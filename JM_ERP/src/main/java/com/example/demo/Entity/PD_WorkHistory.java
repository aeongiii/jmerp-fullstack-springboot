package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Column;

@Entity
public class PD_WorkHistory {
	
	@Id
	@OneToOne
	@JoinColumn(name="ProdCode", referencedColumnName="ProdCode")
	private BOM ProdCode;
	
	
	private String ProdName;
	
	private String Work;
	
	private String WorkCode;
	
	private String WorkName;
	
	private Integer Num;
	
	private String ResName;
	
	private Integer WorkTime;
	
	private Integer whTO;
	
}
