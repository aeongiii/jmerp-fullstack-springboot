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
public class NBProduct {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "product_id")
	    private Long id;

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
	    
	    @ManyToOne
	    @JoinColumn(name = "seller_id")
	    private Seller seller;
}
