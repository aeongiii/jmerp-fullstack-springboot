# 그냥 그래프만 출력하는 파이썬 코드 (스프링부트와 연결 X)
# 회원별 상품구매내역 -- 카테고리별 파이차트 생성
 
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

try:
    # 데이터베이스에 연결
    conn = mysql.connector.connect(**db_config)
    print("MySQL 데이터베이스에 성공적으로 연결되었습니다.")
    cursor = conn.cursor()
    
   # 모든 회원의 ID를 가져오는 쿼리
    cursor.execute("SELECT member_id FROM SD_Member")
    member_ids = [member_id[0] for member_id in cursor.fetchall()]

    for memberId in member_ids:
        # 각 회원별 구매 카테고리별 횟수를 가져오는 쿼리
        query = """
        SELECT NB.category, COUNT(*) as count
        FROM SD_Purchase P
        JOIN SD_NBProduct NB ON P.product_code = NB.product_code
        WHERE P.member_id = %s
        GROUP BY NB.category
        """
        cursor.execute(query, (memberId,))
        
        categories = []
        counts = []
        for category, count in cursor:
            categories.append(category)
            counts.append(count)

        if counts:  # 구매 내역이 있는 경우에만 그래프 생성
            # 원형 그래프 생성
            plt.figure(figsize=(10, 7))
            plt.pie(counts, labels=categories, autopct='%1.1f%%', startangle=140)
            #plt.title(f'[{memberId}] 회원의 카테고리별 구매 비율')

            # 그래프 이미지 저장
            save_path = os.path.join(r'C:\Users\040\git\JumptoMoon\JM_ERP\src\main\resources\static\img', f"SD_graph_{memberId}.png")
            plt.savefig(save_path, dpi=300, bbox_inches='tight')
            plt.close()

    print("모든 그래프 이미지가 저장되었습니다.")

except mysql.connector.Error as e:
    print(f"데이터베이스 연결 실패: {e}")
finally:
    if conn.is_connected():
        conn.close()
        print("데이터베이스 연결이 종료되었습니다.")