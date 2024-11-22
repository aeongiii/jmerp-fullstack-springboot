package com.example.demo.jmerpfullstackspringboot2.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AC_WithholdingForm {

    private String name;

    private String departName;

    // 급여, 국민연금, 건강보험, 장기요양, 고용보험, 소득세, 공제액 합계, 실 수령액
    private int pay;
    private int nationalInsurance;
    private int healthInsurance;
    private int longCareInsurance;
    private int employmentInsurance;
    private int incomeTax;
    private int deduction;
    private int netIncome;
}
