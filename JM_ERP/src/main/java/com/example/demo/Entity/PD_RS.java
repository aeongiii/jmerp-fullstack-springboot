package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class PD_RS {
	@Id @GeneratedValue
	private String id;
	
	@Column(name="rel_num")
	private String relNum;
	
	@Column(name="sto_num")
	private String stoNum;
	
	@Column(name="r_from")
	private String rFrom;
	
	@Column(name="s_to")
	private String sTO;
	
	@Column(name="prod_name")
	private String prodName;
	
	@Column(name="num")
	private Integer num;
	
	@Column(name="prod_Cost")
	private Integer prodCost;
	
	@Column(name="out_src_cost")
	private Integer outSrcCost;
	
	@Column(name="out_src_tot")
	private Integer outSrcTot;	
	
}
