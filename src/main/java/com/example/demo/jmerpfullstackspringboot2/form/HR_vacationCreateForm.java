package com.example.demo.jmerpfullstackspringboot2.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class HR_vacationCreateForm {

    @NotEmpty(message = "사원번호는 필수항목입니다.")
    private String employeeId;

//	private LocalDate workDate;

    private String name;

    private String attendance;

}
