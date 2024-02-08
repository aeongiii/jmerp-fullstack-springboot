package com.example.demo.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.PD_BOM;
import com.example.demo.Entity.PD_Cost;
import com.example.demo.Repository.PD_CostRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PD_CostService {

	
	private final PD_CostRepository costrepository;
	
	public List<PD_Cost> getList(){
		return costrepository.findAll();
	}
	
	public Page<PD_Cost> getList(int page){
		Pageable pageable = PageRequest.of(page, 10);
		return costrepository.findAll(pageable);
	}
	
	public PD_Cost create(PD_BOM pd_bom, String prodName, int kg, int money) {
		PD_Cost cost = new PD_Cost();
		cost.setPd_bom(pd_bom);
		cost.setProdName(prodName);
		cost.setKg(kg);
		cost.setCost(money);
		this.costrepository.save(cost);
		return cost;
	}
}
