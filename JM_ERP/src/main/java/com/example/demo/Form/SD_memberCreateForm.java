package com.example.demo.Form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SD_memberCreateForm {

	@NotEmpty(message = "아이디는 필수항목입니다.")
	private String memberId;	// 기본키 : 고객 아이디

	@NotEmpty(message = "이름은 필수항목입니다.")
    private String memberName;	// 고객이름

	@NotEmpty(message = "전화번호는 필수항목입니다.")
    private String memberPhoneNumber;	// 고객전화번호

	@NotEmpty(message = "이메일은 필수항목입니다.")
    private String memberEmail;	// 고객이메일

	@NotEmpty(message = "주소는 필수항목입니다.")
    private String memberAddress;	// 고객주소

    private LocalDate memberJoinDate;	// 고객가입날짜
    
    public void getMemberId(String memberId) {
		this.memberId = memberId;
	}
}