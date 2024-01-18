package com.example.demo.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;

import lombok.Getter;


@Entity
public class PD_res {
	
	@Id
	private int ResCode;
	
	private String ResName;
	
	private String Location;
	
	private String Do;
}
