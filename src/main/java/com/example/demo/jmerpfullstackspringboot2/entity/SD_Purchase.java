package com.example.demo.jmerpfullstackspringboot2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "SD_Purchase") // 테이블명 지정
public class SD_Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // 한 고객이 여러 구매내역을 가질 수 있음
    @JoinColumn(name = "memberId", referencedColumnName = "memberId")
    private SD_Member memberId; // 고객 아이디 (외래키로 참조)

    @ManyToOne // 한 판매자의 사업자등록번호가 여러번 기록될 수 있음 (여러 사람이 구매할 경우)
    @JoinColumn(name = "sellerId", referencedColumnName = "sellerId")
    private SD_Seller sellerId; // 사업자등록번호 (외래키로 참조)

    private String productCode; // 상품코드

    private int priceEA; // 1개당 가격

    private int totalPurchaseEA; // 구매수량

    private int totalPrice; // 총판매금액

    private LocalDateTime purchaseTime; // 거래일시

    private String memberCardNumber; // 결제한 카드번호

    private String sellerName; // 판매자 이름

}