package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class PD_WorkHistory {
	
	@Id
	@Column(name="res_name")
	private String resName;
	
	@Column(name="pd_res")
	private String pd_res; 
	
	@Column(name="prod_code")
	private String prodCode;
	
	@Column(name="prod_name")
	private String prodName;
	
	@Column(name="work")
	private String Work;
	
	@Column(name="work_code")
	private String workCode;
	
	@Column(name="work_name")
	private String workName;
	
	@Column(name="num")
	private Integer num;
	
	@Column(name="work_time")
	private Integer workTime;
	
	@Column(name="wh_to")
	private Integer whTo;
	
}
