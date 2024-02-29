package com.example.demo.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity 
@Table(name = "SD_Seller")		// 테이블명 지정
public class SD_Seller {

    @Id
    private String sellerId; // 사업자등록번호 (123-4567-8989 형식)

    @Column (columnDefinition = "TEXT")
    private String sellerName;	// 판매자 이름

    private String sellerPhoneNumber;	// 판매자 연락처

    private String sellerEmail;	// 판매자 이메일

    @Column (columnDefinition = "TEXT")
    private String sellerAddress;	// 판매자 주소

    private LocalDate sellerJoinDate;	// 판매자 등록 날짜

}