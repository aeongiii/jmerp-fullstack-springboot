package com.example.demo.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity 
@Table(name = "HR_work")
public class HR_work {
	
	@Id
	private int id;					// 고유번호
	
	@ManyToOne	// 한 사원이 여러 근무기록을 가질 수 있음
	@JoinColumn(name = "employeeId", referencedColumnName = "employeeId")
	private HR_mem employeeId;		// 사원번호 (외래키로 참조)
	
	private LocalDate workDate;		// 근무일 (YYYY-MM-DD)
	
	private String name;			// 사원명
	
	private LocalTime startTime;	// 출근시간 (HH:MM:SS)
	
	private LocalTime endTime;		// 퇴근시간 (HH:MM:SS)
	
	private int workHour;			// 정규근무시간
	
	private int overtimeHour;		// 특별근무시간
	
	private String overtimeType;	// 수당항목
	
	private int overtimePay;		// 특별근무지급액
	
	private String attendance;		// 근태내역
}
