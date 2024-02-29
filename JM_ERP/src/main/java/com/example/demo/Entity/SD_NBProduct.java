package com.example.demo.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
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
@Table(name = "SD_NBProduct") // 테이블명 지정
public class SD_NBProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne // 한 판매자가 여러 상품을 판매할 수 있음
	@JoinColumn(name = "sellerId", referencedColumnName = "sellerId")
	private SD_Seller sellerId; // 사업자등록번호 (외래키로 참조)

	private String sellerName; // 판매자이름

	private String category; // 상품분류

	@Column (columnDefinition = "TEXT")
	private String productName; // 상품명

	private String productCode; // 상품코드 (pb_1 형식)

	private int priceEA; // 1개당 판매금액

	private int totalSaleEA = 0; // 총 판매수량

	private int totalPrice; // 총 가격

	private LocalDate registrationDate; // 상품등록날짜 (2024-01-01 형식)

	@Column (columnDefinition = "TEXT")
	private String imageFileLink; // 이미지파일 링크
	
	@Column (columnDefinition = "TEXT")
	private String description; // 적요

}