package com.example.demo.Form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HR_deptCreateForm {
	@NotEmpty(message = "이름은 필수항목입니다.")
	private String deptName;
	
	private String deptCode;
}
