import pandas as pd
from statsmodels.tsa.arima.model import ARIMA
import matplotlib.pyplot as plt
import matplotlib.font_manager as fm
from statsmodels.graphics.tsaplots import plot_acf, plot_pacf
from statsmodels.graphics.tsaplots import acf, pacf
from statsmodels.tsa.stattools import adfuller
import json
import warnings

# 경고 메시지 무시 설정
warnings.filterwarnings("ignore")


# CSV 파일 경로는 절대경로나 상대경로로 수정해야 할 수 있습니다.
df = pd.read_csv("C:/Users/040/Desktop/asdf/asdf.csv", encoding="CP949")

# 특정 판매자 ID를 제외한 데이터프레임 생성
filtered_df = df[df['seller_name'] != "달토끼"]

category = ["도서", "패션", "공구", "리빙", "식품", "전자기기"]

# 특정 purchase_time과 특정 product_code 별로 purchaseea의 합을 계산하여 새로운 데이터프레임 생성
new_df = filtered_df.groupby(['purchase_time', 'product_code'])['total_purchaseea'].sum().reset_index()

# new_df를 월별로 나누고 전반기, 중반기, 후반기로 나누기
new_df['purchase_time'] = pd.to_datetime(new_df['purchase_time'])
new_df['month'] = new_df['purchase_time'].dt.month

# 1~10일은 전반기, 11~20일은 중반기, 21일 이후는 후반기로 지정
new_df['half'] = '전반기'
new_df.loc[(new_df['purchase_time'].dt.day >= 11) & (new_df['purchase_time'].dt.day <= 20), 'half'] = '중반기'
new_df.loc[new_df['purchase_time'].dt.day > 20, 'half'] = '후반기'
new_df['half'] = new_df['month'].astype(str) + '월 ' + new_df['half']

# 월별 판매량 계산 및 컬럼명 지정
monthly_sales = new_df.groupby(['product_code', 'half'])['total_purchaseea'].sum().reset_index()

results = {}
# 각 제품에 대한 예측값 플롯
for product_code in category:
    product_sales = monthly_sales[monthly_sales['product_code'] == product_code]['total_purchaseea']
    
    # ACF 및 PACF 그래프 그리기
    acf_values, confint = acf(product_sales, fft=True, alpha=0.05)
    pacf_values, confint = pacf(product_sales, alpha=0.05)

    # ACF 및 PACF 그래프에서 처음으로 음수가 되는 lag 찾기
    first_negative_acf_lag = next((i for i, x in enumerate(acf_values) if x < 0), 0)
    first_negative_pacf_lag = next((i for i, x in enumerate(pacf_values) if x < 0), 0)

    # Augmented Dickey-Fuller (ADF) Test를 통한 단위근 검정
    result = adfuller(product_sales)

    # p-value가 유의수준보다 큰 경우
    if result[1] > 0.05:
        d = 1  # 추가적인 차분을 시도할 수 있음
    else:
        d = 0  # 정상성을 만족하므로 추가적인 차분은 필요하지 않음

    # ARIMA 모델 학습
    model = ARIMA(product_sales, order=(first_negative_acf_lag, d, first_negative_pacf_lag))
    fit_model = model.fit()
    forecast = fit_model.forecast(steps=18)
    results[product_code] = forecast.tolist()

# 결과를 JSON 형태로 출력
print(json.dumps(results))
















# import pandas as pd
# from statsmodels.tsa.arima.model import ARIMA
# import matplotlib.pyplot as plt
# import matplotlib.font_manager as fm
# from statsmodels.graphics.tsaplots import plot_acf, plot_pacf
# from statsmodels.graphics.tsaplots import acf, pacf
# from statsmodels.tsa.stattools import adfuller

# # 나눔 폰트 경로 설정
# # font_path = '/usr/share/fonts/truetype/nanum/NanumGothic.ttf'
# # font_prop = fm.FontProperties(fname=font_path, size=12)
# # 데이터 파일 읽어오기
# df = pd.read_csv("C:/Users/040/Desktop/asdf/asdf.csv", encoding="CP949")


# # 특정 판매자 ID를 제외한 데이터프레임 생성
# filtered_df = df[df['seller_name'] != "달토끼"]

# category = ["도서", "패션", "공구", "리빙", "식품", "전자기기"]

# # 특정 purchase_time과 특정 product_code 별로 purchaseea의 합을 계산하여 새로운 데이터프레임 생성

# new_df = filtered_df.groupby(['purchase_time', 'product_code'])['total_purchaseea'].sum().reset_index()

# # new_df를 월별로 나누고 전반기, 중반기, 후반기로 나누기
# new_df['purchase_time'] = pd.to_datetime(new_df['purchase_time'])
# new_df['month'] = new_df['purchase_time'].dt.month

# # 1~10일은 전반기, 11~20일은 중반기, 21일 이후는 후반기로 지정
# new_df['half'] = '전반기'
# new_df.loc[(new_df['purchase_time'].dt.day >= 11) & (new_df['purchase_time'].dt.day <= 20), 'half'] = '중반기'
# new_df.loc[new_df['purchase_time'].dt.day > 20, 'half'] = '후반기'
# new_df['half'] = new_df['month'].astype(str) + '월 ' + new_df['half']

# # 월별 판매량 계산 및 컬럼명 지정
# monthly_sales = new_df.groupby(['product_code', 'half'])['total_purchaseea'].sum().reset_index()

# print(monthly_sales)

# # 특정 상품코드 선택
# for product_code in category:

#   product_sales = monthly_sales[monthly_sales['product_code'] == product_code]['total_purchaseea']

#   # ACF 및 PACF 그래프 그리기
#   acf_values, confint = acf(product_sales, fft=True, alpha=0.05)
#   pacf_values, confint = pacf(product_sales, alpha=0.05)

#   # ACF 및 PACF 그래프에서 처음으로 음수가 되는 lag 찾기
#   first_negative_acf_lag = next((i for i, x in enumerate(acf_values) if x < 0), 0)
#   first_negative_pacf_lag = next((i for i, x in enumerate(pacf_values) if x < 0), 0)

#   print("First negative ACF lag:", first_negative_acf_lag)
#   print("First negative PACF lag:", first_negative_pacf_lag)

#   # Augmented Dickey-Fuller (ADF) Test를 통한 단위근 검정
#   result = adfuller(product_sales)
#   print('ADF Statistic:', result[0])
#   print('p-value:', result[1])
#   print('Critical Values:')
#   for key, value in result[4].items():
#       print('\t{}: {}'.format(key, value))

#   # p-value가 유의수준보다 큰 경우
#   if result[1] > 0.05:
#       d = 1  # 추가적인 차분을 시도할 수 있음
#   else:
#       d = 0  # 정상성을 만족하므로 추가적인 차분은 필요하지 않음

#   # ARIMA 모델 학습
#   model = ARIMA(product_sales, order=(first_negative_acf_lag, d, first_negative_pacf_lag))  # ARIMA 모델의 파라미터는 임의로 지정한 것이므로 적절한 파라미터를 선택해야 합니다.
#   fit_model = model.fit()

#   # 예측 수행
#   forecast = fit_model.forecast(steps=18)

#   # 예측 결과 출력
#   plt.plot(range(1, 19), forecast, label='Predicted Sales')
#   plt.title(f'Sales Forecast for Product {product_code}')
#   plt.xlabel('Month')
#   plt.ylabel('Sales')
#   plt.legend()
#   plt.xticks(range(1, 19, 3), range(1, 7)) 
#   # 예측 값 저장
#   predicted_values = []
#   for i in range(0, len(forecast), 3):
#       sum_values = sum(forecast[i:i+3])
#       rounded_sum = round(sum_values)
#       predicted_values.append(rounded_sum)

# #   plt.savefig(f'{product_code} 상품 예측 그래프.png')
# #   plt.show()

#   # 저장된 예측 값 출력
#   print("Predicted Values :")
#   for month, value in enumerate(predicted_values, start=1):
#       print(f"Month {month}: {value}")

# results = {}







# # 각 제품에 대한 예측값 플롯
# for product_code in category:
#     product_sales = monthly_sales[monthly_sales['product_code'] == product_code]['total_purchaseea']
    
#     # ARIMA 모델 학습
#     model = ARIMA(product_sales, order=(first_negative_acf_lag, d, first_negative_pacf_lag))
#     fit_model = model.fit()
    
#     # 예측 수행
#     forecast = fit_model.forecast(steps=18)


#     results[product_code] = {
#         "predicted_values": forecast.tolist(),  # 예측 값 리스트로 변환하여 저장
#         # "actual_values": 실제 값 리스트로 변환하여 저장 (예시, 실제 값을 어떻게 구하는지에 따라 다름)
#     }
    
#     # 저장된 예측 값 출력 (옵션)
#     #print(f"Product Code: {product_code}, Predicted Values: {forecast.tolist()}")
# print(results)
    
#     # # 예측값 플롯
#     # plt.plot(range(1, 19), forecast, label=f'Product {product_code}')

# # # 그래프 타이틀과 레이블 설정
# # plt.title('Sales Forecast for Products')
# # plt.xlabel('Month')
# # plt.ylabel('Sales')
# # plt.legend(loc='upper right', bbox_to_anchor=(1.3, 1))
# # plt.xticks(range(1, 19, 3), range(1, 7))

# # # 그래프 저장
# # plt.tight_layout()
# # plt.savefig('Combined_Forecast_Graph.png')

# # # 그래프 표시
# # plt.show()
