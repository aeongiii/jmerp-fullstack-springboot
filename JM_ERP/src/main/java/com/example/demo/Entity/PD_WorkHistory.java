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
	
	@ManyToOne
	@MapsId  // @Id로 지정한 컬럼에 @OneToOne 이나 @ManyToOne 관계를 매핑시키는 역할
	@JoinColumn(name="res_name")
	private PD_res pd_res; 
	
	@ManyToOne
	@JoinColumn(name = "prod_code")
	private BOM prodCode;
	
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
