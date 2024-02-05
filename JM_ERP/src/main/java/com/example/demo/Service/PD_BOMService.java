package com.example.demo.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.PD_BOM;
import com.example.demo.Repository.PD_BOMRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PD_BOMService {
	
	private final PD_BOMRepository bomrepository;

    public List<PD_BOM> getList() {
        return bomrepository.findAll();
    }
    
    public Page<PD_BOM> getList(int page) {
    	Pageable pageable = PageRequest.of(page, 10);
    	return bomrepository.findAll(pageable);
    }
    
    public PD_BOM create(String prodCode, String prodName, int rawMatNum, 
    		String unit, int num, String type, int rawNum, int workTime) {
    	PD_BOM bom = new PD_BOM();
    	bom.setProdCode(prodCode);
    	bom.setProdName(prodName);
    	bom.setRawMatNum(rawMatNum);
    	bom.setUnit(unit);
    	bom.setNum(num);
    	bom.setType(type);
    	bom.setRawNum(rawNum);
    	bom.setWorkTime(workTime);
    	this.bomrepository.save(bom);
    	return bom;
    }
}
