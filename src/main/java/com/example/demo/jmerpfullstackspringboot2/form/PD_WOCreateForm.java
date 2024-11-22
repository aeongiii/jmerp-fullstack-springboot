package com.example.demo.jmerpfullstackspringboot2.form;

import com.example.demo.jmerpfullstackspringboot2.entity.PD_BOM;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PD_WOCreateForm {

    @NotEmpty(message = "필수항목입니다.")
    private String orderNum;

    @NotEmpty
    private String deliveryName;

    @NotEmpty
    private String manager;

    @NotEmpty
    private Integer deliveryDate;

    @NotEmpty
    private PD_BOM prodCode;

    @NotEmpty
    private Integer wOrder;

    @NotEmpty
    private Integer making;

    @NotEmpty
    private String factory;

}
