package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class BOM {
	
	@Id
	private String ProdCode;
	
	private String ProdName;
	
	private String ProdLine;
	
	private Integer RawMatNum;
	
	private String Unit;
	
	private Integer Num;
	
	private String Type;
	
	private Integer RawNum;
	
	private Integer WorkTime;
}
