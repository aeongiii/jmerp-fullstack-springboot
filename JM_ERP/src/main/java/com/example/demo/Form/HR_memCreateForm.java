package com.example.demo.Form;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HR_memCreateForm {
	
	@NotEmpty(message = "이름은 필수항목입니다.")
	private String name;
	
	@NotEmpty(message = "부서는 필수항목입니다.")
	private String deptName;
	
	@NotEmpty(message = "직급은 필수항목입니다.")
	private String position;
	
	@NotEmpty(message = "이메일은 필수항목입니다.")
	@Email
	private String email;
	
	@NotNull(message = "입사일은 필수항목입니다.")
	private LocalDate startDate;
	
	@NotEmpty(message = "계좌번호는 필수항목입니다.")
	private String bankNum;
	
	@NotNull(message = "기본급은 필수항목입니다.")
	private int regularPay;
	
	private String employeeId;
	
	public void getEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

}
