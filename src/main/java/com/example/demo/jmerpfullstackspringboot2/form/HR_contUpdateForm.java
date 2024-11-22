package com.example.demo.jmerpfullstackspringboot2.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class HR_contUpdateForm {
    @NotNull(message = "근로계약서 등록일은 필수항목입니다.")
    private LocalDate contractDate;

    @NotEmpty(message = "근로계약서 이름은 필수항목입니다.")
    private String contractName;

    @NotEmpty(message = "사원번호는 필수항목입니다.")
    private String employeeId;

    private String name;

    private String signA;

    private String signB;

    private int id;
}
