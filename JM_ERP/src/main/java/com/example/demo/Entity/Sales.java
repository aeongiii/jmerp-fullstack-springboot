package com.example.demo.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity_sold", nullable = false)
    private Integer quantitySold;

    @Column(name = "sale_date")
    private LocalDate saleDate;

    @Column(name = "total_sale_amount")
    private Double totalSaleAmount; 

    // Constructors, Getters, and Setters
}

