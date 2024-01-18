package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.Getter;

@Entity
public class PD_WorkHistory {
	
	@Id
	private String ProdCode;
	
	private String ProdName;
	
	private String Work;
	
	private String WorkCode;
	
	private String WorkName;
	
	private int Num;
	
	private String ResName;
	
	private int WorkTime;
	
	private int TO;
	
}
