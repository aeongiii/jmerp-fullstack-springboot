# 스프링부트와 파이썬 연결하여 그래프 출력
# 회원별 상품구매내역 -- 카테고리별 파이차트 생성


import sys
import json
import matplotlib.pyplot as plt
import pandas as pd

# 인자로 받은 파일 경로
data_file_path = sys.argv[1]    # JSON 문자열로 변환된 데이터의 주소
output_image_path = sys.argv[2] # 그래프를 이미지 파일로 저장할 경로

# 파일에서 JSON 데이터 읽기
with open(data_file_path, 'r', encoding='utf-8') as file:
    data = json.load(file)

# 데이터 분리 (카테고리 이름과 값)
    categories = list(data.keys())
    counts = list(data.values())

 # 원형 그래프 생성
    plt.figure(figsize=(10, 7))
    plt.pie(counts, labels=categories, autopct='%1.1f%%', startangle=140)
    plt.title('카테고리별 구매 비율')

# 그래프를 이미지 파일로 저장
plt.savefig(output_image_path, dpi=300)
