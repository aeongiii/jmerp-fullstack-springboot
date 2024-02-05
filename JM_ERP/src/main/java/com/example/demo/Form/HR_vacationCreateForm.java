package com.example.demo.Form;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HR_vacationCreateForm {

	@NotEmpty(message = "사원번호는 필수항목입니다.")
	private String employeeId;
	
//	private LocalDate workDate;
	
	private String name;
	
	private String attendance;
	
}
