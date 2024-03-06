import sys
import json
import matplotlib.pyplot as plt
import pandas as pd

# 인자로 받은 파일 경로
data_file_path = sys.argv[1]
output_image_path = sys.argv[2]

# 파일에서 JSON 데이터 읽기
with open(data_file_path, 'r', encoding='utf-8') as file:   # CP949 오류 있어서 UTF-8로 변경
    data = json.load(file)

# pandas DataFrame으로 변환
df = pd.DataFrame(data)

# 상품별 총 구매수량으로 그래프 생성
df.groupby('productCode')['totalPurchaseEA'].sum().plot(kind='bar')

# 그래프를 이미지 파일로 저장
plt.savefig(output_image_path)
