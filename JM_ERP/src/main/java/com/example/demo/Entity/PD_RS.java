package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PD_RS {
	@Id @GeneratedValue
	private String id;
	
	private String relNum;
	
	private String stoNum;
	
	private String rFrom;
	
	private String sTO;
	
	@ManyToOne
	@JoinColumn(name = "prod_name")
	private BOM prodName;
	
	private Integer num;
	
	private Integer prodCost;
	
	private Integer outSrcCost;
	
	private Integer outSrcTot;	
	
}
