# 스프링부트와 파이썬 연결하여 그래프 출력
# 카테고리별 판매량 -- 막대그래프 생성

import sys
import json
import matplotlib.pyplot as plt
import pandas as pd

# 인자로 받은 파일 경로
data_file_path = sys.argv[1]    # JSON 문자열로 변환된 데이터의 주소
output_image_path = sys.argv[2] # 그래프를 이미지파일로 저장할 경로
target_category = sys.argv[3]   # "공구"

# 파일에서 JSON 데이터 읽기
with open(data_file_path, 'r', encoding='utf-8') as file:   # CP949 오류 있어서 UTF-8로 변경
    data = json.load(file)

# pandas DataFrame으로 변환
df = pd.DataFrame(data)

# 카테고리에 따른 데이터 필터링
filtered_df = df[df['category'] == target_category]

# productName마다 totalSaleEA의 값을 합산하여, '상품별 총 구매수량' 막대 그래프 생성!
filtered_df.groupby('productName')['totalSaleEA'].sum().plot(kind='bar')

#plt.title('카테고리 상품별 판매량 비교')  # 제목
plt.ylabel('제품명')   # Y축 이름 설정
plt.xlabel('총 판매량')          # X축 이름 설정
plt.xticks(rotation=45)             # 라벨 객체의 이름을 45도 회전(겹치지 않도록)
plt.tight_layout()                  # 그래프 레이아웃을 자동으로 조정 (범위 넘어가지 않도록)

# 그래프를 이미지 파일로 저장
plt.savefig(output_image_path)
