package com.example.demo.jmerpfullstackspringboot2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "HR_dept")        // 테이블명 지정
public class HR_dept {
    @Id
    private String deptName;    // 부서명

    private String deptCode;    // 부서코드


    public String toString() {
        return this.deptName;
    }
}
