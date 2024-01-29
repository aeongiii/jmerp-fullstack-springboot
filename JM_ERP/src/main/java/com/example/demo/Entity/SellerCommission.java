package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "SellerCommission")
public class SellerCommission {
   
	@Id
	private Long num;
    
	@OneToOne
    @JoinColumn(name = "seller_id")
    private Seller seller; // 셀러 엔티티 참조

    @Column(name = "total_sales")
    private Double totalSales; // 총 판매액

    @Column(name = "paid_commission")
    private Double paidCommission; // 지불한 수수료

    // Constructors, Getters, Setters
}


