package com.example.demo.jmerpfullstackspringboot2.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalTime;

@Getter
@Setter
public class HR_workUpdateForm {
    @NotEmpty(message = "사원번호는 필수항목입니다.")
    private String employeeId;

    private int id;
    private String name;

    private LocalTime startTime;
    private LocalTime endTime;

//	private LocalDate workDate;

    private int workHour;

    private String overtimeType;
    private int overtimeHour;
    private int overtimePay;
}