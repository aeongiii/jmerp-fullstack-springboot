package com.example.demo.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "AC_deposit_silp")
@Getter
@Setter
public class AC_DepositSlip {

    @Id
    private String slipCode; // 전표 코드

    private LocalDate tradeDate; // 거래일자
    private String trader; // 거래처
    
    private int descriptionEA; // 적요에 넣을 갯수
    
    @Column(columnDefinition = "TEXT")
    private String description; // 적요
    
    private int amount; // 금액
    private String transactionType; // 거래유형

    private LocalDateTime createdAt; // 생성일자
}
