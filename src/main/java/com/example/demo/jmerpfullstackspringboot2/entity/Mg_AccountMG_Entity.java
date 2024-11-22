package com.example.demo.jmerpfullstackspringboot2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;


@Getter
@Setter
@Entity
@Table(name = "Mg_AccountMg")
public class Mg_AccountMG_Entity {
    public Mg_AccountMG_Entity() {

    }


    @Id
    private Long accountCode;
    //거래처 사업자 번호

    @Pattern(regexp = "^[0-9]{3}-[0-9]{4}-[0-9]{4}$", message = "Invalid pattern")
    private String accountNum;
    //거래처 전화번호
    @Column
    private String accountName;
    //거래처 이름
    @Column
    private String accountManager;
    //거래처 담당자 명

    public Mg_AccountMG_Entity(Long accountCode, String accountNum, String accountName, String accountManager) {
        this.accountCode = accountCode;
        this.accountNum = accountNum;
        this.accountName = accountName;
        this.accountManager = accountManager;
    }
}