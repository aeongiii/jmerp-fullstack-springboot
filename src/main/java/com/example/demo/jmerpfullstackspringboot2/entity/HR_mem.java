package com.example.demo.jmerpfullstackspringboot2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "HR_mem")        // 테이블명 지정
public class HR_mem {
    @Id
    private String employeeId;        // 기본키 : 사원번호

    private String name;            // 사원명

    private LocalDate startDate;    // 입사일 (YYYY-MM-DD)

    @ManyToOne    // 부서 안에는 여러 사원이 포함될 수 있음
    @JoinColumn(name = "deptName", referencedColumnName = "deptName")
    private HR_dept deptName;        // 부서명	(외래키로 참조)

    private String position;        // 직급

    private String email;            // 이메일

    private int regularPay = 0;            // 기본급

    private String bankNum;            // 계좌번호


    public String toString() {
        return this.employeeId;
    }

    public String getId() {
        // TODO Auto-generated method stub
        return null;
    }
}
