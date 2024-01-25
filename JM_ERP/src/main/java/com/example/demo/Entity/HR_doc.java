package com.example.demo.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
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
@Table(name="HR_doc")
public class HR_doc {
	@Id
	private String docNum;		// 증명서 발행번호 (2023-1)
	
	@ManyToOne	// 한 사원이 여러개의 증명서를 발행할 수 있음(여러 행에 추가될 수 있음)
	@JoinColumn(name="employeeId", referencedColumnName="employeeId")
	private HR_mem employeeId;		// 샤원번호
	
	private String name;			// 사원이름
			
	private String docType;			// 증명서 종류
	
	@Column(nullable = true)	// NULL 가능
	private String docUse;			// 증명서 용도
	
	private LocalDate docDate;		// 증명서 발행 날짜
}
