package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Mg_WMS {
	
	@Id
	@Column
	private String wareName;
	
	@Column
	private String wareLocation;
	
	@Column
	private String wareReciving;
	
	@Column
	private String wareRElese;
	
}
