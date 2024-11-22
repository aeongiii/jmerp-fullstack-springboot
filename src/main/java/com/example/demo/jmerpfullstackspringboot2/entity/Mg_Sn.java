package com.example.demo.jmerpfullstackspringboot2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity

@Getter
@Setter
public class Mg_Sn {


    @ManyToOne
    @JoinColumn(name = "wmsItemCode")
    private Mg_item_Regi itemCode;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer snCount;
    //시리얼 넘버 부여를 위한

    @Column
    private LocalDate snDate;
    //날짜
    @Column
    private String item_name;
    //이름
}
