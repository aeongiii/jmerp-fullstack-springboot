package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Mg_AccountMg")
public class Mg_AccountMG_Entity {
	@Id
	private Integer accountCode;
	
	@Column
	private Integer accoungNum;
	
	@Column
	private String accountName;
	
	@Column
	private String accountManager;
	}


