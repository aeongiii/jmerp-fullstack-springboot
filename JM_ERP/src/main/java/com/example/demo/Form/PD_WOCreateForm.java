package com.example.demo.Form;

import javax.validation.constraints.NotEmpty;

import com.example.demo.Entity.PD_BOM;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PD_WOCreateForm {
	
	@NotEmpty(message="필수항목입니다.")
	private String orderNum;
	
	@NotEmpty
	private String deliveryName;
	
	@NotEmpty
	private String manager;
	
	@NotEmpty
	private Integer deliveryDate;
	
	@NotEmpty
	private PD_BOM prodCode;
	
	@NotEmpty
	private Integer wOrder;
	
	@NotEmpty
	private Integer making;
	
	@NotEmpty
	private String factory;

}
