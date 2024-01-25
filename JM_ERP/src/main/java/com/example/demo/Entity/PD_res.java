package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class PD_res {
	
	@Id
	@Column(name="res_name")
	private String resName;

	@Column(name="res_code")
	private Integer resCode;
	
	
	@Column(name="location")
	private String Location;
	
	@Column(name="do")
	private String Do;
}
