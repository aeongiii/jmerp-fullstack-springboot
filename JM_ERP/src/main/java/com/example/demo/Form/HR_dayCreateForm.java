package com.example.demo.Form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HR_dayCreateForm {
	@NotEmpty(message = "일용근무사원 부서는 필수항목입니다.")
	private String deptName;
	
	@NotNull(message = "일용근무 시간은 필수항목입니다.")
	private int dayWorkHour;
	
	@NotEmpty(message = "일용근무사원 이름은 필수항목입니다.")
	private String dayWorkName;
	
	@NotNull(message = "일용근무 수당은 필수항목입니다.")
	private int dayWorkPay;
	
	@NotNull(message = "일용근무 날짜는 필수항목입니다.")
	private LocalDate dayWorkDate;
	
	private int id;
}
