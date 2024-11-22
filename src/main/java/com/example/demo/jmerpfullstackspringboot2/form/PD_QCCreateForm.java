package com.example.demo.jmerpfullstackspringboot2.form;

import com.example.demo.jmerpfullstackspringboot2.entity.PD_BOM;
import com.example.demo.jmerpfullstackspringboot2.entity.PD_QCregi;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PD_QCCreateForm {

    @NotEmpty
    private Integer id;

    @NotEmpty
    private String qcTool;

    @NotEmpty
    private PD_BOM prodCode;

    @NotEmpty
    private Integer prodNum;

    @NotEmpty
    private Integer qcNum;

    @NotEmpty
    private PD_QCregi qcList;

    @NotEmpty
    private String pF;
}
