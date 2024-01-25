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
	//거래처 사업자 번호
	@Column(length = 20)
	private Long accountNum;
	//거래처 전화번호
	@Column
	private String accountName;
	//거래처 이름
	@Column
	private String accountManager;
	//거래처 담당자 명
	}


