package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity

public class Mg_Sn {

	@ManyToOne
    @JoinColumn(name = "itemCode", referencedColumnName = "itemCode")
    private Mg_item_Regi mgItemRegi;
	//품목구분 코드 포린키
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer snCount;
	//시리얼 넘버 부여를 위한 카운트
	@Column
	private String snReceipt;
	//영수증
}
