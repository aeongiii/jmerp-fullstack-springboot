package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.Getter;

@Entity
public class PD_OS2AC {
	@Id
	private String OSNum;
	
	private String OSAccount;
	
	private Integer OSCost;
	
	// 조정
	// 공급가액
	
	private Integer OSbill;
	
	private Integer OSTot;
	
	// 회계전표No.
}
