package com.example.demo.jmerpfullstackspringboot2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PD_QC {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "qc_tool")
    private String qcTool;

    @ManyToOne
    @JoinColumn(name = "prod_code")
    private PD_BOM prodCode;

    @Column(name = "prod_num")
    private Integer prodNum;

    @Column(name = "qc_num")
    private Integer qcNum;

    @ManyToOne
    @JoinColumn(name = "qc_list")
    private PD_QCregi qcList;

    @Column(name = "p_f")
    private String pF;


}
