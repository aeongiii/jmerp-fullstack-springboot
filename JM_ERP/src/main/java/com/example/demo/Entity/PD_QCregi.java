package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.Getter;

@Entity
public class PD_QCregi {

	@Id
	private String QCCode;
	
	private String QCName;

	private String QCList;
	
	private String QCProd;
}
