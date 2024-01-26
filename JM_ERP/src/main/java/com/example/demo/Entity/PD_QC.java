package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PD_QC {
	@Id
	@Column(name="qc_tool")
	private String qcTool;
	
	@Column(name="prod_code")
	private String prodCode;
	
	@Column(name="prod_name")
	private String prodName;
	
	@Column(name="prod_num")
	private Integer prodNum;
	
	@Column(name="qc_num")
	private Integer qcNum;
	
	@Column(name="p_f")
	private String pF;
	
	@Column(name="qc_list")
	private String qcList;
}
