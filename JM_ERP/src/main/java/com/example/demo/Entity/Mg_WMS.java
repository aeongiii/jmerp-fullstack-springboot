package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Mg_WMS {
	
	@Id
	@Column
	private String wareName;
	//창고명
	@Column
	private String wareLocation;
	//창고위치
	@Column
	private String wareReciving;
	//입고날짜
	@Column
	private String wareRelese;
	//출고날짜
	@Column
	private String Itemname;
	//물품명
	
	@ManyToOne
	@JoinColumn(name = "wmsItemCode", referencedColumnName = "itemCode")
	private Mg_item_Regi wmsItemCode;
	//물품코드
	@Column
	private Integer itemCost;
	//물품원가
	@Column
	private String itemType;
	//물품종류
	@Column
	private Integer itemNumber;
	//물품 개수
}
