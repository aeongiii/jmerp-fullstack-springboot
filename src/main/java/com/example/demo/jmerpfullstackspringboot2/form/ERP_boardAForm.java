package com.example.demo.jmerpfullstackspringboot2.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ERP_boardAForm {

    @NotEmpty(message = "내용은 필수항목입니다.")
    private String content;
}
