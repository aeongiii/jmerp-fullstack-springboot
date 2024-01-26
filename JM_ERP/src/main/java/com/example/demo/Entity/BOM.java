package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Getter;

@Getter
@Entity
public class BOM {
	
	@Id
	@Column(name="prod_code")
	private String prodCode;
	
	@Column(name="prod_name")
	private String prodName;
	
	@Column(name="prod_line")
	private String prodLine;
	
	@Column(name="raw_mat_num")
	private Integer rawMatNum;
	
	@Column(name="unit")
	private String unit;
	
	@Column(name="num")
	private Integer num;
	
	@Column(name="type")
	private String type;     
	
	@Column(name="raw_num")
	private Integer rawNum;
	
	@Column(name="work_time")
	private Integer workTime;
}
