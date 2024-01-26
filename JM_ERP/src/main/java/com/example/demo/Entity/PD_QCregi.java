package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PD_QCregi {
	
	@Column(name="qc_code")
	private String qcCode;
	
	@Column(name="qc_name")
	private String qcName;

	@Id
	@Column(name="qc_list")
	private String qcList;
	
	@Column(name="qc_prod")
	private String qcProd;
}
