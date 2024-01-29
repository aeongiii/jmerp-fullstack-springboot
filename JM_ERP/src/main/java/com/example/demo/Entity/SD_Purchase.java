package com.example.demo.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SD_Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private SD_Member member;
   
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private SD_Seller seller;
  
    @ManyToOne
    @JoinColumn(name = "product_id")
    private SD_Product product;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    @Column(name = "purchase_amount")
    private Integer purchaseAmount;

    @Column(name = "transaction_time")
    private LocalDateTime transactionTime;

    @Column(name = "cancellation_return_info")
    private boolean cancellationReturnInfo;

    @Column(name = "card_type", length = 50)
    private String cardType;

    @Column(name = "card_number", length = 20)
    private String cardNumber;

   
}
