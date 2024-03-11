# 그냥 그래프만 출력하는 파이썬 코드 (스프링부트와 연결 X)
# 카테고리별 총 판매량 -- 꺾은선그래프 생성
 
import mysql.connector
import matplotlib.pyplot as plt
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
categories = ['공구', '도서', '리빙', '식품', '전자기기', '패션']

# 카테고리별 총 판매량을 저장할 딕셔너리
total_sales_by_category = {}

try:
    # 데이터베이스에 연결
    conn = mysql.connector.connect(**db_config)
    print("MySQL 데이터베이스에 성공적으로 연결되었습니다.")
    cursor = conn.cursor()
    
    # 모든 카테고리에 대해 총 판매량 쿼리 실행
    for category in categories:
        query = f"SELECT SUM(total_saleea) FROM SD_NBProduct WHERE category = '{category}'"
        cursor.execute(query)
        total_sale = cursor.fetchone()[0] or 0  # 결과가 None이면 0으로 처리
        total_sales_by_category[category] = total_sale
    
    # 꺾은선 그래프 생성
    plt.figure(figsize=(10, 8))
    plt.plot(list(total_sales_by_category.keys()), list(total_sales_by_category.values()), marker='o', linestyle='-')
    #plt.title('카테고리별 총 판매량 비교')
    plt.xlabel('카테고리')
    plt.ylabel('총 판매량')
    plt.xticks(rotation=45)
    plt.grid(True)

    # 그래프를 이미지 파일로 저장
    save_path = os.path.join(r'C:\Users\040\git\JumptoMoon\JM_ERP\src\main\resources\static\img', 'SD_graphNB.png')
    plt.savefig(save_path, dpi=300, bbox_inches='tight')
    plt.close()

    print(f"카테고리별 총 판매량 비교 그래프 이미지가 저장되었습니다: {save_path}")

except mysql.connector.Error as e:
    print(f"데이터베이스 연결 실패: {e}")
finally:
    if conn.is_connected():
        conn.close()
        print("데이터베이스 연결이 종료되었습니다.")
