# 스프링부트와 파이썬 연결하여 그래프 출력
# 카테고리별 총 판매량 -- 꺾은선그래프 생성

import sys
import json
import matplotlib.pyplot as plt
import pandas as pd

# 인자로 받은 파일 경로
data_file_path = sys.argv[1]    # JSON 문자열로 변환된 데이터의 주소
output_image_path = sys.argv[2] # 그래프를 이미지 파일로 저장할 경로
categories = sys.argv[3].split(",") # 콤마로 구분된 카테고리 이름을 배열로 변환

# 파일에서 JSON 데이터 읽기
with open(data_file_path, 'r', encoding='utf-8') as file:
    data = json.load(file)

# pandas DataFrame으로 변환
df = pd.DataFrame(data)

# 카테고리별 총 판매량 합산
category_totals = df[df['category'].isin(categories)].groupby('category')['totalSaleEA'].sum()

# 꺾은선 그래프 생성
category_totals.plot(kind='line', marker='o')

#plt.title("카테고리별 총 판매량 비교")  # 그래프 제목
plt.ylabel('총 판매량')   # Y축 이름 설정
#plt.xlabel('카테고리')    # X축 이름 설정
plt.xticks(range(len(categories)), categories, rotation=45) # X축 눈금 라벨 설정
plt.tight_layout()                  # 그래프 레이아웃을 자동으로 조정 (범위 넘어가지 않도록)

# 그래프를 이미지 파일로 저장
plt.savefig(output_image_path, dpi=300)
