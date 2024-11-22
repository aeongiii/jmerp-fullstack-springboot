package com.example.demo.jmerpfullstackspringboot2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "AC_debt")
@Getter
@Setter
public class AC_Debt {

    @Id
    private String debtNumber; // 채무 번호를 id로 사용

    private LocalDate date; // 채무 생성 날자
    private String trader;
    private Double amount; //(빌리거나 빌려준 돈의 최초 금액)
    private Double increaseDecreaseType; //(빚의 추가 및 할인)
    private Double balance; //(남은 금액)
    private LocalDate maturityDate;

    @Column(columnDefinition = "TEXT")
    private String description; // 적요에는 갚은 날짜를 입력

    // 생성자, 필요한 메소드는 롬복이 자동으로 생성해주므로 생략
}

