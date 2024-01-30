package com.example.demo.Entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Use_Self {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer useCount;
	//pk적용 순서
	
	@Column
	private String useWareName;
	//사용 창고명
	
	@Column
	private Date usDate;
	//사용 날짜
	@Column
	private Integer useNum;
	//사용 갯수
	
	@Column
	private String usName;
	//사용 물품 명
}
