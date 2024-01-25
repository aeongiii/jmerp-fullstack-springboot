package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class BOM {
	
	@Id
	@Column(name = "prod_code")
	private String prodCode;
	
	private String prodName;
	
	private String prodLine;
	
	private Integer rawMatNum;
	
	private String unit;
	
	private Integer num;
	
	private String type;     
	
	private Integer rawNum;
	
	private Integer workTime;
}
