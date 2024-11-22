package com.example.demo.jmerpfullstackspringboot2.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class HR_deptUpdateForm {
    @NotEmpty(message = "부서의 새로운 이름을 입력해주세요.")
    private String newDeptName;

    private String oldDeptName;
}
