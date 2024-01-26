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
public class PD_Cost {
	
	@Id
	@Column(name="prod_code")
	private String prodCode;
	
	@OneToOne
	@MapsId
	@JoinColumn(name="prod_code")
	private PD_BOM pd_bom;
	
	@Column(name="prod_name")
	private String prodName;
	
	@Column(name="kg")
	private Integer kg;
	
	@Column(name="cost")
	private Integer cost;
}	
