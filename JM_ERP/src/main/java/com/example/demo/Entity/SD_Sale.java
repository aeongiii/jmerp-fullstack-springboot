package com.example.demo.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "SD_Sale") // 테이블명 지정
public class SD_Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne // 한 고객이 여러 번 구매할 수 있음
	@JoinColumn(name = "memberId", referencedColumnName = "memberId")
	private SD_Member memberId; // 고객 아이디 (외래키로 참조)

	@ManyToOne // 한 판매자의 사업자등록번호가 여러번 기록될 수 있음 (여러 번 판매될 경우)
	@JoinColumn(name = "sellerId", referencedColumnName = "sellerId")
	private SD_Seller sellerId; // 사업자등록번호 (외래키로 참조)

	private String productCode; // 상품코드

	private int productEA; // 구매수량

	private int purchaseAmount; // 구매금액

	private LocalDateTime purchaseTime; // 거래일시

	private String cardNumber; // 결제한 카드번호

}
