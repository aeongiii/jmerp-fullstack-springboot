package com.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.PD_BOM;
import com.example.demo.Repository.PD_BOMRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BOMService {
	
	private final PD_BOMRepository bomrepository;

    public List<PD_BOM> getList() {
        return bomrepository.findAll();
    }
    
    public List<PD_BOM> getItem(String prodCode) {
    	return bomrepository.findByProdCode(prodCode);
    }
}
