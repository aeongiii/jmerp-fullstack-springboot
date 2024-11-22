package com.example.demo.jmerpfullstackspringboot2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PD_WorkHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "res_name")
    private PD_res res;

    @ManyToOne
    @JoinColumn(name = "prod_code")
    private PD_BOM bom;

    @Column(name = "work")
    private String work;

    @Column(name = "work_code")
    private String workCode;

    @Column(name = "num")
    private Integer num;

    @Column(name = "work_time")
    private Integer workTime;

    @Column(name = "wh_to")
    private Integer whTo;

}
