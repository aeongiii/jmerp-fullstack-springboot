package com.example.demo.jmerpfullstackspringboot2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "mg_use_self")
public class Use_Self {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer useCount;
    //pk적용 순서

    @Column
    private String useWareName;
    //사용 창고명

    @Column
    private LocalDate usDate;
    //사용 날짜
    @Column
    private Integer useNum;
    //사용 갯수

    @Column
    private String usName;
    //사용 물품 명
}
