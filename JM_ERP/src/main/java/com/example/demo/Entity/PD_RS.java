package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.Getter;

@Entity
public class PD_RS {
	
	private String RelNum;
	
	private String StoNum;
	
	private String From;
	
	private String To;
	
	private String ProdName;
	
	private int Num;
	
	private int ProdCost;
	
	private int OutSrcCost;
	
	private int OutSrcTot;	
	
}
