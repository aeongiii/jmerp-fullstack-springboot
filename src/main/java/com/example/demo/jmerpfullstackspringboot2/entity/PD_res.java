package com.example.demo.jmerpfullstackspringboot2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PD_res {

    @Id
    @Column(name = "res_name")
    private String resName;

    @Column(name = "res_code")
    private Integer resCode;


    @Column(name = "location")
    private String Location;

    @Column(name = "do")
    private String Do;

}
