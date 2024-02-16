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

@Entity

@Getter
@Setter
public class Mg_Sn {


	@ManyToOne
	@JoinColumn(name = "wmsItemCode")
	private Mg_item_Regi itemCode;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer snCount;
	//시리얼 넘버 부여를 위한 

	@Column
	private LocalDate snDate;
	//날짜
	@Column
	private String item_name;
	//이름
}
