package com.example.demo.Form;

import javax.validation.constraints.NotEmpty;

import com.example.demo.Entity.PD_BOM;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PD_CostCreateForm {
	
	
	@NotEmpty
	private PD_BOM pd_bom; // prodCode 참조
	
	@NotEmpty
	private String prodName;
	
	@NotEmpty
	private Integer kg;
	
	@NotEmpty
	private Integer cost;
}
