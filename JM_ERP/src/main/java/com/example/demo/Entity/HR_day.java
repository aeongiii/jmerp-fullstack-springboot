package com.example.demo.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="HR_day")
public class HR_day {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// id값 자동 증가
	private int id;					// 고유번호
	
	private LocalDate dayWorkDate; 	// 일용근무일
	
	private String deptName;		// 부서명 (외래키 X 독립적으로 사용)
	
	private String dayWorkName;		// 일용직 사원 이름
	
	private int dayWorkHour = 0;		// 일용근무 시간
	
	private int dayWorkPay = 0;			// 일용근무 지급액
	
}
