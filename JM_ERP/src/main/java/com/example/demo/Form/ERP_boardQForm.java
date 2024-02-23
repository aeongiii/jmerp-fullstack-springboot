package com.example.demo.Form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ERP_boardQForm {
	
	@NotEmpty(message="제목은 필수항목입니다.")
	@Size(max=200)
	private String subject;
	
	@NotEmpty(message="내용은 써주셔야죠.")
	private String content;
	
}
