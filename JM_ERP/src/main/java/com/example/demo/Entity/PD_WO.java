package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.Getter;

@Entity
public class PD_WO {

	@Id
	private String rrderNum;
	
	private String deliveryName;
	
	private String manager;
	
	private Integer deliveryDate;
	
	
	private String prodCode;
	
	private String prodName;
	
	private Integer wOrder;
	
	private Integer Making;
	
	private String Factory;
}
