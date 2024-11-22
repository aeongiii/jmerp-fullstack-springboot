package com.example.demo.jmerpfullstackspringboot2.form;

import com.example.demo.jmerpfullstackspringboot2.entity.PD_BOM;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PD_CostCreateForm {


    @NotEmpty
    private PD_BOM pd_bom; // prodCode 참조

    @NotEmpty
    private String prodName;

    @NotEmpty
    private Integer kg;

    @NotEmpty
    private Integer cost;
}
