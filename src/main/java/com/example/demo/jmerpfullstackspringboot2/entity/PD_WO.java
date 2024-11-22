package com.example.demo.jmerpfullstackspringboot2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PD_WO {

    @Id
    @Column(name = "order_num")
    private String orderNum;

    @Column(name = "delivery_name")
    private String deliveryName;

    @Column(name = "manager")
    private String manager;

    @Column(name = "delivery_date")
    private Integer deliveryDate;

    @ManyToOne
    @JoinColumn(name = "prod_code")
    private PD_BOM prodCode;

    @Column(name = "w_order")
    private Integer wOrder;

    @Column(name = "making")
    private Integer making;

    @Column(name = "factory")
    private String factory;
}
