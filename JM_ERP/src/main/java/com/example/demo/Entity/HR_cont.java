package com.example.demo.Entity;

import java.time.LocalDate;

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
@Table (name="HR_cont")
public class HR_cont {

	@Id
	private int id;						// 고유번호
	
	private LocalDate contractDate;		// 근로계약서 등록일
	
	private String contractName;		// 근로계약서명
	
	@ManyToOne	// 한 사원이 여러 근무기록을 가질 수 있음
	@JoinColumn(name = "employeeId", referencedColumnName = "employeeId")
	private HR_mem employeeId;			// 사원번호 (외래키로 참조)
	
	private String name;				// 사원이름
	
	private boolean signA;				// 서명요청여부
	
	private boolean signB;				// 서명완료여부
	
}
