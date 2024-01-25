package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class PD_QC {
	@Id
	private String qcTool;
	
	private String prodCode;
	
	private String prodName;
	
	private Integer prodNum;
	
	private Integer qCNum;
	
	private String pF;
	
	private String qcList;
}
