package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Mg_item_Price {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer number;

    @ManyToOne
    @JoinColumn(name = "itemCode", referencedColumnName = "itemCode")
    private Mg_item_Regi mgItemRegi;

	@Column
	private Integer itemCost;
}

