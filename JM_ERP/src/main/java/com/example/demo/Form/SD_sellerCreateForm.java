package com.example.demo.Form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SD_sellerCreateForm {

	@NotEmpty(message = "사업자등록번호는 필수항목입니다.")
    private String sellerId; // 사업자등록번호 (123-4567-8989 형식)

	@NotEmpty(message = "판매자명은 필수항목입니다.")
    private String sellerName;	// 판매자 이름

	@NotEmpty(message = "전화번호는 필수항목입니다.")
    private String sellerPhoneNumber;	// 판매자 연락처

	@NotEmpty(message = "이메일은 필수항목입니다.")
    private String sellerEmail;	// 판매자 이메일

	@NotEmpty(message = "주소는 필수항목입니다.")
    private String sellerAddress;	// 판매자 주소

    private LocalDate sellerJoinDate;	// 판매자 등록 날짜
}
