package com.example.demo.Form;

import javax.validation.constraints.NotEmpty;

import com.example.demo.Entity.PD_BOM;
import com.example.demo.Entity.PD_QCregi;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PD_QCCreateForm {

	@NotEmpty
	private Integer id;
	
	@NotEmpty
	private String qcTool;
	
	@NotEmpty
	private PD_BOM prodCode;
	
	@NotEmpty
	private Integer prodNum;
	
	@NotEmpty
	private Integer qcNum;
	
	@NotEmpty
	private PD_QCregi qcList;
	
	@NotEmpty
	private String pF;
}
