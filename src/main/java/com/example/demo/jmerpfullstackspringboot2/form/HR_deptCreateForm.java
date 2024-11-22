package com.example.demo.jmerpfullstackspringboot2.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class HR_deptCreateForm {
    @NotEmpty(message = "이름은 필수항목입니다.")
    private String deptName;

    private String deptCode;
}

