package com.example.demo.Form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HR_docCreateForm {
	
	
	@NotEmpty(message = "사원번호는 필수항목입니다.")
	private String employeeId;		// 샤원번호
	
	@NotEmpty(message = "증명서 종류는 필수항목입니다.")
	private String docType;			// 증명서 종류
	
	@NotEmpty(message = "증명서 용도는 필수항목입니다.")
	private String docUse;			// 증명서 용도
	
	@NotNull(message = "증명서 등록일은 필수항목입니다.")
	private LocalDate docDate;		// 증명서 등록 날짜
	
	private String name;			// 사원이름
	
	private String docNum;		// 증명서 발행번호 (2023-1)
}
