# 그냥 그래프만 출력하는 파이썬 코드 (스프링부트와 연결 X)
# pb 상품별 판매량 -- 막대그래프 생성

import mysql.connector
import matplotlib.pyplot as plt
import matplotlib.font_manager as fm
import os

# 한글 폰트 설정
plt.rc('font', family='Malgun Gothic')

# 데이터베이스 연결 설정
db_config = {
    'user': 'root',  # MySQL 사용자 이름
    'password': '1234',  # MySQL 비밀번호
    'host': 'localhost',  # MySQL 서버 주소 (리모트 호스트인 경우 IP 주소나 도메인)
    'database': 'jm_erp',  # 접근할 데이터베이스 이름
    'port': 3306  # MySQL 포트 번호 (기본값은 3306)
}


# 카테고리 목록
products = ['플레인 닭가슴살', '양념 닭가슴살', '스모크 닭가슴살', '슬라이스 닭가슴살', '큐브 닭가슴살']

try:
    # 데이터베이스에 연결
    conn = mysql.connector.connect(**db_config)
    print("MySQL 데이터베이스에 성공적으로 연결되었습니다.")
    cursor = conn.cursor()
    
    # 쿼리 실행
    query = f"SELECT product_name, total_saleea FROM SD_PBProduct"
    cursor.execute(query)

    # 결과 저장
    product_names = []
    total_sale_ea = []
    for (product_name, total_saleea) in cursor:
        product_names.append(product_name)
        total_sale_ea.append(total_saleea)

    # total_sale_ea의 최댓값을 찾아서 각 그래프 y축의 최댓값 설정
    max_value = max(total_sale_ea)

    # 막대그래프 생성
    plt.figure(figsize=(10,8))
    plt.bar(product_names, total_sale_ea, color='skyblue')
    #plt.title('자체제작 상품별 판매량 비교')
    plt.xlabel('제품명')
    plt.ylabel('총 판매량(EA)')
    plt.xticks(rotation=45)

    # y축 범위 설정 (최대값의 10%를 추가하여 여유를 줌)
    plt.ylim(0, max_value + (max_value * 0.1))

    # 그래프를 이미지 파일로 저장
    file_name = f"SD_graphPB.png"
    save_path = os.path.join(r'C:\Users\040\git\JumptoMoon\JM_ERP\src\main\resources\static\img', file_name)
    plt.savefig(save_path, dpi=300, bbox_inches='tight')
    plt.close()

    print("자체제작 상품별 그래프 이미지가 저장되었습니다: {save_path}")

except mysql.connector.Error as e:
    print(f"데이터베이스 연결 실패: {e}")
finally:
    if conn.is_connected():
        conn.close()
        print("데이터베이스 연결이 종료되었습니다.")
