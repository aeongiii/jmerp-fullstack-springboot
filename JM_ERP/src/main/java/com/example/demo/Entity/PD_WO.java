package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.Getter;

@Entity
public class PD_WO {

	@Id
	private String OrderNum;
	
	private String DeliveryName;
	
	private String Manager;
	
	private int DeliveryDate;
	
	
	private String ProdCode;
	
	private String ProdName;
	
	private int Order;
	
	private int Making;
	
	private String Factory;
}
