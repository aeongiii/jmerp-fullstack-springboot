package com.example.demo.Entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Debt")
@Getter
@Setter
public class AC_debt {

    @Id
    private String debtNumber; // 채무 번호를 id로 사용

    private Date date;
    private String counterpartyName;
    private Double amount;
    private String increaseDecreaseType;
    private Date maturityDate;
    private String description;
    private String transactionType;

    // 생성자, 필요한 메소드는 롬복이 자동으로 생성해주므로 생략
}

