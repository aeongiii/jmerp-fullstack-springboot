package com.example.demo.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
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
public class ERP_userMailBox {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long num;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "userId")
	private ERP_user reciveUser;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "userId")
	private ERP_user sendUser;
	
	private String subject;
	 
	private String content;
	
	private String image; 
	
	private String mediaFile; 
	
	private boolean checkStatus;
	
	private LocalDateTime sendTransaction;
}

