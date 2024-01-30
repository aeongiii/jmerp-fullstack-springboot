package com.example.demo.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "AC_purchase_silp")
@Getter
@Setter
public class AC_purchaseSlip {

    @Id
    private String slipCode; // 전표 코드

    private LocalDate tradeDate; // 거래일자
    private String trader; // 거래처
    private String description; // 적요
    private Double amount; // 금액
    private Double VAT; // 부가세
    private String cardNum; // 카드번호
    private String cardName; // 카드명
    private String transactionType; // 거래유형

    private LocalDateTime createdAt; // 생성일자
    private LocalDateTime updatedAt; // 수정일자
}
