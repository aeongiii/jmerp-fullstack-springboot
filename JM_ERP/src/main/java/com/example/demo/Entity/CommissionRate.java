package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CommissionRates")
@Getter
@Setter
public class CommissionRate {

   
	@Id
    @Column(name = "category", nullable = false, length = 50)
    private String category;

    @Column(name = "rate", nullable = false)
    private Double rate;

    
}

