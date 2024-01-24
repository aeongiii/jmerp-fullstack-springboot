package com.example.demo.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity 
@Table(name = "HR_mem")		// 테이블명 지정
public class HR_mem {
	@Id
	private String employeeId;

	private String name;
	
	private LocalDate startDate;		// 날짜
	
	@JoinColumn(name = "deptName", referencedColumnName = "deptName")
	private String deptName;
	
	private String position;
	
	private String email;
	
	private int regularPay;
	
	private String bankNum;
}
