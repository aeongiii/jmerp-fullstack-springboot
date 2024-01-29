package com.example.demo.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class SD_Seller {

    @Id
    @Column(name = "seller_id", nullable = false)
    private String sellerId; // 'seller'에서 'sellerId'로 변경

    @Column(name = "business_number", nullable = false)
    private String businessNumber;

    @Column(name = "contact_info", nullable = false)
    private String contactInfo;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "ceo_name", nullable = false)
    private String ceoName;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "contract", nullable = false)
    private boolean contract;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate; // 'date'에서 'registrationDate'로 변경

    // 다른 필드 및 메서드
}
