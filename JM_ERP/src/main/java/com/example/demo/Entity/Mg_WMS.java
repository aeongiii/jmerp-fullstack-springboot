package com.example.demo.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Mg_WMS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer wareNumber;
	// 순서 저장pk
	
	@Column
	private String wareName;
	// 창고명
	
	@Column
	private String wareLocation;
	// 창고위치
	
	@Column
	private LocalDate wareReciving;
	// 입고날짜
	
	@Column
	private LocalDate wareRelese;
	// 출고날짜
	
	@Column
	private String ItemName;
	// 물품명

	@ManyToOne
	@JoinColumn(name = "wmsItemCode")
	private Mg_item_Regi itemCode;
	// 물품코드
	
	@Column
	private Integer itemCost;
	// 물품원가
	
	@Column
	private String itemType;
	// 물품종류
	
	@Column
	private Integer itemNumber;
//	 물품 개수
	
}
