package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PD_BOM {
	
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
