package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.Getter;

@Entity
public class PD_OS2AC {

	private String OSNum;
	
	private String OSAccount;
	
	private int OSCost;
	
	// 조정
	// 공급가액
	
	private int OSbill;
	
	private int OSTot;
	
	// 회계전표No.
}
