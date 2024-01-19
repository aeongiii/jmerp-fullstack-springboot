package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "PB_Products")
@Getter
@Setter
public class PBproduct {

    @Id
    @Column(name = "Product_Code", length = 20)
    private String productCode;

    @Column(name = "Product_Name", nullable = false, length = 100)
    private String productName;
    
    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "Release_Date")
    private LocalDate releaseDate;

    @Column(name = "Description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "Category", length = 50)
    private String category;

  
}

