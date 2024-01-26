package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PD_WorkHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="res_name")
	private PD_res resName;
	
	@ManyToOne
	@JoinColumn(name="prod_code")
	private PD_BOM prodCode;
	
	@Column(name="work")
	private String Work;
	
	@Column(name="work_code")
	private String workCode;
	
	@Column(name="num")
	private Integer num;
	
	@Column(name="work_time")
	private Integer workTime;
	
	@Column(name="wh_to")
	private Integer whTo;
	
}
