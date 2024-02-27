package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "SD_PBProduct") // 테이블명 지정
public class SD_PBProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne // 자사 상품에서는 사업자등록번호가 하나뿐이지만 혹시 모를 예외상황에 대비해 ManyToOne으로 넣었음
	@JoinColumn(name = "sellerId", referencedColumnName = "sellerId")
	private SD_Seller sellerId; // 사업자등록번호 (외래키로 참조)

	private String category; // 상품분류

	private String productName; // 상품명

	private String productCode; // 상품코드 (pb_1 형식)

	private int PriceEA; // 1개당 판매금액

	private LocalDate registrationDate; // 상품등록날짜 (2024-01-01 형식)
	
	private String imageFileLink; // 이미지파일 링크
	
	private String description; // 적요
	
	private int totalSaleEA; // 총 판매된 수량

  
}
