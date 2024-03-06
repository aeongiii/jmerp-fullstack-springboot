package com.example.demo.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "AC_month")
@Getter
@Setter
public class AC_Month {

    @Id
    private String monthId; // 년/월 (예: "202401")

    private int revenue; // 수익 - deposit
    private int VAT; // 부가세(금) - deposit
    private int expense; // 비용(지출) - withdrawal
    private int netIncome; // 순수익 revenue - VAT - withdrawal

    private LocalDateTime updatedAt; // 수정일자

    // 생성자, 필요한 메소드는 롬복이 자동으로 생성해주므로 생략
}