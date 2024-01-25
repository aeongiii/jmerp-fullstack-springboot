package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PD_WorkHistory {
	
	@Id
	private String ProdCode;
	
	private String ProdName;
	
	private String Work;
	
	private String WorkCode;
	
	private String WorkName;
	
	private Integer Num;
	
	private String ResName;
	
	private Integer WorkTime;
	
	private Integer whTO;
	
}
