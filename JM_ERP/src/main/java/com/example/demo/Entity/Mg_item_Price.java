package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Mg_item_Price {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer priceNumber;
	//PK 순서 저장
    @ManyToOne
    @JoinColumn(name = "pricItemCode", referencedColumnName = "itemCode")
    private Mg_item_Regi mgItemRegi;
    //포린키 regi의 ItemCode 와 연결된 품목구분코드
	@Column
	private Integer itemCost;
	//단가 저장
	@Column
	private String priceItemName;
	//물품명
}

