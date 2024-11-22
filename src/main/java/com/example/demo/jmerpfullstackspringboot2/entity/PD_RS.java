package com.example.demo.jmerpfullstackspringboot2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PD_RS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rel_num")
    private String relNum;

    @Column(name = "sto_num")
    private String stoNum;

    @Column(name = "r_from")
    private String rFrom;

    @Column(name = "s_to")
    private String sTO;

    @ManyToOne
    @JoinColumn(name = "prod_code")
    private PD_BOM prodCode;

    @Column(name = "num")
    private Integer num;

    @Column(name = "prod_Cost")
    private Integer prodCost;

}
