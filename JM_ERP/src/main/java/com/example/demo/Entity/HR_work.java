package com.example.demo.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity 
@Table(name = "HR_work")
public class HR_work {
	@JoinColumn(name = "employeeId", referencedColumnName = "employeeId")
	private String employeeId;
	
	private LocalDate workDate;		// 날짜 2024-01-01 형식으로 저장됨
	
	private String name;
	
	private LocalTime startTime;	// 시간 09:00:00 형식으로 저장됨
	
	private LocalTime endTime;		// 시간
	
	private int workHour;
	
	private int overtimeHour;
	
	private String overtimeType;
	
	private int overtimePay;
	
	private String attendance;
}
