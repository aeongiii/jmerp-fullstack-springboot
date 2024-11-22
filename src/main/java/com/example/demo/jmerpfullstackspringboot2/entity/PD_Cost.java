package com.example.demo.jmerpfullstackspringboot2.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PD_Cost {

    @Id
    @Column(name = "prod_code")
    private String prodCode;

    @OneToOne
    @MapsId
    @JoinColumn(name = "prod_code")
    private PD_BOM pd_bom;

    @Column(name = "prod_name")
    private String prodName;

    @Column(name = "kg")
    private Integer kg;

    @Column(name = "cost")
    private Integer cost;
}	
