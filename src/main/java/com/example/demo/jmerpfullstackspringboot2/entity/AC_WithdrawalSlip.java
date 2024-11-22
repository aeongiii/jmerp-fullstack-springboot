package com.example.demo.jmerpfullstackspringboot2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "AC_withdrawal_slip")
@Getter
@Setter
public class AC_WithdrawalSlip {

    @Id
    private String slipCode; // 전표 코드

    private LocalDate tradeDate; // 거래일자
    private String trader; // 거래처

    @Column(columnDefinition = "TEXT")
    private String description; // 적요

    private int amount; // 금액
    private String transactionType; // 거래유형

    private LocalDateTime createdAt; // 생성일자
}
