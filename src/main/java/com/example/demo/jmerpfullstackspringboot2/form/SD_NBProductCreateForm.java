package com.example.demo.jmerpfullstackspringboot2.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class SD_NBProductCreateForm {

    @NotEmpty(message = "사업자등록번호는 필수항목입니다.")
    private String sellerId; // 사업자등록번호 (외래키로 참조)

    @NotEmpty(message = "판매자명은 필수항목입니다.")
    private String sellerName; // 판매자이름

    @NotEmpty(message = "상품분류는 필수항목입니다.")
    private String category; // 상품분류

    @NotEmpty(message = "상품명은 필수항목입니다.")
    private String productName; // 상품명

    private String productCode; // 상품코드 (pb_1 형식) 자동생성

    @NotNull(message = "개당 판매금액은 필수항목입니다.")
    private int priceEA = 0; // 1개당 판매금액

    private int totalSaleEA = 0; // 총 판매수량

    private int totalPrice = 0; // 총 가격

    private LocalDate registrationDate; // 상품등록날짜 (2024-01-01 형식) 자동생성

    private String imageFileLink; // 이미지파일 링크

    private String description; // 적요

    private int id;
}
