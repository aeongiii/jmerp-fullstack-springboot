package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class PD_QC {
	@Id
	private String QCTool;
	
	private String ProdCode;
	
	private String ProdName;
	
	private Integer ProdNum;
	
	private Integer QCNum;
	
	private String P_F;
	
	private String QCList;
}
