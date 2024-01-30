package com.example.demo.Entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PC_PurchaseInquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num")
    private Long num;
    
    @Column(name = "purchase_date", nullable = false)
    private LocalDate PurchaseDate;

    @Column(name = "client_name", nullable = false, length = 100)
    private String clientName;

    @Column(name = "item_name", nullable = false, length = 200)
    private String itemName;

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    @Column(name = "transaction_type", length = 50)
    private String transactionType;

    @Column(name = "warehouse_name", length = 100)
    private String warehouseName;

    @Column(name = "accounting_reflection_status", length = 50)
    private String accountingReflectionStatus;

    @Column(name = "imported_voucher", length = 100)
    private String importedVoucher;

    // 기본 생성자, getter, setter는 Lombok 어노테이션을 통해 자동 생성됩니다.
}
