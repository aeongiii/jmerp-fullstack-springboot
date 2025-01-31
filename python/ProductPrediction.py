import pandas as pd
import pymysql
import json
import os
import platform
from sqlalchemy import create_engine
from statsmodels.tsa.arima.model import ARIMA
import matplotlib.pyplot as plt
import matplotlib.font_manager as fm
from statsmodels.tsa.stattools import adfuller

# 한글 폰트 설정
if platform.system() == "Windows": # 윈도우
    font_path = "C:/Windows/Fonts/malgun.ttf"
elif platform.system() == "Darwin":  # 맥
    font_path = "/Library/Fonts/AppleGothic.ttf"
else:  # 리눅스
    font_path = "/usr/share/fonts/truetype/nanum/NanumGothic.ttf"

font_prop = fm.FontProperties(fname=font_path, size=12)

# MySQL 연결 정보
DB_URL = "mysql+pymysql://root:1234@localhost:3306/jm_erp"
engine = create_engine(DB_URL)
print("📌 MySQL 연결 성공")

# 1년간 판매 데이터 조회
query = """
SELECT purchase_time, product_code, seller_name, total_purchaseea 
FROM sd_purchase
WHERE purchase_time >= DATE_SUB(NOW(), INTERVAL 1 YEAR)
"""
df = pd.read_sql(query, engine)
print("📌 데이터 로드 완료")

# 컬럼명 변환 및 날짜 변환
df.columns = df.columns.str.lower()
df['purchase_time'] = pd.to_datetime(df['purchase_time'])

# 6개 카테고리 하드코딩
category_mapping = {
    "A001": "공구", "A002": "도서", "A003": "리빙",
    "A004": "식품", "A005": "전자기기", "A006": "패션"
}

# product_code -> 카테고리 매핑
df['category'] = df['product_code'].apply(lambda x: category_mapping.get(x, "기타"))

# 카테고리별 월별 판매량 집계
df['month'] = df['purchase_time'].dt.to_period('M')
category_sales = df.groupby(['category', 'month'])['total_purchaseea'].sum().reset_index()

# 예측 결과 저장할 딕셔너리
predictions = {}

# 그래프 저장 경로
output_dir = "static/"
if not os.path.exists(output_dir):
    os.makedirs(output_dir)

# 카테고리별 예측 수행
for category in ["공구", "도서", "리빙", "식품", "전자기기", "패션"]:
    sales_data = category_sales[category_sales['category'] == category]['total_purchaseea'].values

    if len(sales_data) < 3:
        print(f"📌 {category} - 데이터 개수 부족으로 분석 건너뜀")
        continue

    try:
        # 정상성 검정 (데이터가 너무 적을 경우 예외처리)
        try:
            result = adfuller(sales_data)
            d = 1 if result[1] > 0.05 else 0  # p-value가 0.05보다 크면 차분 필요
        except Exception:
            d = 1  # 데이터가 적으면 기본적으로 차분 적용

        # ARIMA 모델 학습 및 예측 수행
        model = ARIMA(sales_data, order=(1, d, 1))
        fit_model = model.fit()
        forecast = fit_model.forecast(steps=6)

        # 예측 결과 저장
        predictions[category] = list(map(int, forecast))

        # 그래프 저장 (파일명 인코딩 문제 방지)
        safe_category_name = category.replace(" ", "_")  # 공백 제거
        plt.figure(figsize=(6, 4))
        plt.plot(range(1, len(sales_data) + 1), sales_data, label="Actual Sales", marker='o')
        plt.plot(range(len(sales_data) + 1, len(sales_data) + 7), forecast, label="Forecast", linestyle='dashed', marker='x')
        plt.title(f"Sales Forecast for {category}", fontproperties=font_prop)
        plt.xlabel("Month")
        plt.ylabel("Sales")
        plt.legend()
        plt.savefig(f"{output_dir}{safe_category_name}_forecast.png")
        plt.close()

        print(f"📌 {category} - 예측 완료 및 그래프 저장")

    except Exception as e:
        print(f"📌 {category} - 예측 오류: {e}")

# 예측 결과 JSON 저장
with open("predictions.json", "w", encoding="utf-8") as f:
    json.dump(predictions, f, indent=4)

print("📌 예측 결과 저장 완료")