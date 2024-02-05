package com.example.demo.Form;

import java.time.LocalDate;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HR_contCreateForm {

	@NotNull(message = "근로계약서 등록일은 필수항목입니다.")
	private LocalDate contractDate;
	
	@NotEmpty(message = "근로계약서 이름은 필수항목입니다.")
	private String contractName;
	
	@NotEmpty(message = "사원번호는 필수항목입니다.")
	private String employeeId;
	
	private String name;
	
	private String signA;
	
	private String signB;
	
	private int id;
}
