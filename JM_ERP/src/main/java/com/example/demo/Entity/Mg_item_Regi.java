package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Mg_item_Regi {

	@Column
	private String Itemname;
	//물품이름
	@Id
	private String itemCode;
	//물품코드
	@Column
	private Integer itemCost;
//	물품원가
	@Column
	private String itemType;
	//물품 종류
	@Column
	private String itemReciving;
	//물품 등록 날짜
	@Column
	private Integer itemNumber;
	//넘버
}



