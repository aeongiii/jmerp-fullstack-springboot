package com.example.demo.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PD_Cost {
	@Id
	@Column(name="prod_name")
	private String prodName;
	
	@Column(name="kg")
	private Integer kg;
	
	@Column(name="cost")
	private Integer cost;
}	
