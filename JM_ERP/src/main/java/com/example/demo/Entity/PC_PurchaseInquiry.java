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
    //번호
    @Column(name = "purchase_date", nullable = false)
    private LocalDate PurchaseDate;
    //구매일자
    @Column(name = "client_name", nullable = false, length = 100)
    private String clientName;
    //거래처명
    @Column(name = "item_name", nullable = false, length = 200)
    private String itemName;
    //품목명
    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;
    //총 수량

    @Column(name = "warehouse_name", length = 100)
    private String warehouseName;
    // 창고명
    @Column(name = "acceptance", length = 100)
    private String acceptance;
    //전표 카드거래만
    

    // 기본 생성자, getter, setter는 Lombok 어노테이션을 통해 자동 생성됩니다.
}
