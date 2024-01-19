package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Mg_item_Regi {

	@Column
	private String Itemname;
	
	@Id
	private String itemCode;
	
	@Column
	private Integer itemCost;
	
	@Column
	private String itemType;
	
	@Column
	private String itemReciving;
	
	@Column
	private Integer itemNumber;
}



