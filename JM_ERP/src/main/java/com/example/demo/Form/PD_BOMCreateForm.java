package com.example.demo.Form;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PD_BOMCreateForm {
	@NotEmpty(message = "필수항목입니다.")
	private String prodCode;
	
	@NotEmpty
	private String prodName;
	
	@NotEmpty
	private Integer rawMatNum;
	
	@NotEmpty
	private String unit;
	
	@NotEmpty
	private Integer num;
	
	@NotEmpty
	private String type;     
	
	@NotEmpty
	private Integer rawNum;
	
	@NotEmpty
	private Integer workTime;
}
