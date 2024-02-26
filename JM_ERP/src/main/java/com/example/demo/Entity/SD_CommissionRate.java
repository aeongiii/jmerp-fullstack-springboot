package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SD_CommissionRate {

   
	@Id
    @Column(name = "category", nullable = false, length = 50)
    private String category;

    @Column(name = "rate", nullable = false)
    private Double rate;

    
}

// 판매 수수료는 고정할 것 이니 지울 것