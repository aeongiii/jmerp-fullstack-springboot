# 스프링부트와 파이썬 연결하여 그래프 출력
# 카테고리별 총 판매량 -- 꺾은선그래프 생성

import sys
import json
import matplotlib.pyplot as plt
import pandas as pd

# 인자로 받은 파일 경로
data_file_path = sys.argv[1]    # JSON 문자열로 변환된 데이터의 주소
output_image_path = sys.argv[2] # 그래프를 이미지 파일로 저장할 경로
pbProducts = sys.argv[3].split(",") # 콤마로 구분된 카테고리 이름을 배열로 변환

# 파일에서 JSON 데이터 읽기
with open(data_file_path, 'r', encoding='utf-8') as file:
    data = json.load(file)

# pandas DataFrame으로 변환
df = pd.DataFrame(data)

# '상품별 총 구매수량' 막대 그래프 생성!
plt.title(f"Total Sale Quantity by Product in {pbProducts} Category")  # 제목
plt.ylabel('Total Sale Quantity')   # Y축 이름 설정
plt.xlabel('Product Name')          # X축 이름 설정
plt.xticks(rotation=45)             # 라벨 객체의 이름을 45도 회전(겹치지 않도록)
plt.tight_layout()                  # 그래프 레이아웃을 자동으로 조정 (범위 넘어가지 않도록)

# 그래프를 이미지 파일로 저장
plt.savefig(output_image_path, dpi=300)
