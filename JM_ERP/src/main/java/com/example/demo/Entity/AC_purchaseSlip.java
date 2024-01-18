package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "Purchase")
@Getter
@Setter
public class AC_purchaseSlip {

    @Id
    private String slipCode; // 전표 코드

    private Date tradeDate; // 거래일자
    private String trader; // 거래처
    private String description; // 적요
    private Double amount; // 금액
    private Double vat; // 부가세
    private String cardNum; // 카드번호
    private String cardName; // 카드명
    private String transactionType; // 거래유형

    private Date createdAt; // 생성일자
    private Date updatedAt; // 수정일자
}
