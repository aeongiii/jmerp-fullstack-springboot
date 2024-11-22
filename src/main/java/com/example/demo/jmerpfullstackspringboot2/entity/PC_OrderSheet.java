package com.example.demo.jmerpfullstackspringboot2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class PC_OrderSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_number")
    private Long orderNumber;
    //주문번호 순서

    @Column(name = "client_name", nullable = false, length = 100)
    private String clientName;
    //거래처 명

    @Column(name = "contact_person", length = 100)
    private String contactPerson;
    //담당자명

    @Column(name = "item", nullable = false, length = 200)
    private String item;
    // 품목
    @Column(name = "delivery_date", nullable = false)
    private LocalDate deliveryDate;
    //납기일
    @Column(name = "Count", nullable = false)
    private Double Count;
    //수량
    @Column(name = "completion_status", length = 50)
    private String completionStatus;
    //종결여부 예/아니오

    @Column
    private Long totalPrice;


    @Column(length = 100)
    private LocalDate PurchaseDate;


}
