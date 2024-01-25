package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.Getter;

@Entity
public class PD_QCregi {
	
	private String qcCode;
	
	private String qcName;

	@Id
	private String qcList;
	
	private String qcProd;
}
