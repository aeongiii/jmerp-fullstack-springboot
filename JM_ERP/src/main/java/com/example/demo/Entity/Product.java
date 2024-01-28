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


@Entity
@Getter
@Setter
public class Product {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "product_id")
	    private Long id;
		
		@Column(name = "product_type")
	    private String productType;

	    @Column(name = "product_name", nullable = false)
	    private String productName;

	    @Column(name = "price", nullable = false)
	    private Double price;

	    @Column(name = "registration_date", nullable = false)
	    private LocalDate registrationDate;

	    @Column(name = "registration_status", nullable = false)
	    private String registrationStatus;

	    @Column(name = "image_file_link")
	    private String imageFile;
	    
	    @Column(name = "category")
	    private String category;
	    
	    @Column(name = "description")
	    private String description;
	    
	    @ManyToOne
	    @JoinColumn(name = "seller_id")
	    private Seller seller;
}
