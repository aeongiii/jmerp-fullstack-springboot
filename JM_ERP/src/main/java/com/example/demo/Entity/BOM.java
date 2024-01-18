package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.Getter;

@Entity
public class BOM {
	@Id
	private String ProdCode;
	
	private String ProdName;
	
	private String ProdLine;
	
	private int RawMatNum;
	
	private String Unit;
	
	private int Num;
	
	private String Type;
	
	private int RawNum;
	
	private int WorkTime;
}
