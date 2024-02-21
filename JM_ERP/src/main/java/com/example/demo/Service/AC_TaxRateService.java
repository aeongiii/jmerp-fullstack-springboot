package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.AC_TaxRate;
import com.example.demo.Entity.HR_dept;
import com.example.demo.Entity.HR_mem;
import com.example.demo.Form.AC_WithholdingForm;
import com.example.demo.Repository.AC_TaxRateRepository;
import com.example.demo.Repository.HR_memRepository;

import jakarta.persistence.criteria.Join;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AC_TaxRateService {
	
	private final AC_TaxRateRepository taxRateRepository;
	
	private final HR_memRepository memRepository;
	
	public List<HR_mem> searchAll() {
		
		return this.memRepository.findAll();
	}
	
	public List<HR_mem> searchByName(String keyword) {
	    Specification<HR_mem> spec = (root, query, cb) -> {
	        return cb.like(root.get("name"), "%" + keyword + "%");
	    };
	    return memRepository.findAll(spec);
	}
	
	public List<HR_mem> searchByDeptName(String keyword) {
	    Specification<HR_mem> spec = (root, query, cb) -> {
	    	
	    	Join<HR_mem, HR_dept> deptJoin = root.join("deptName");
	    	
	        return cb.like(deptJoin.get("deptName"), "%" + keyword + "%");
	    };
	    return memRepository.findAll(spec);
	}
	
    public Page<AC_WithholdingForm> calculateTax(int page, List<HR_mem> memList) {
        Pageable pageable = PageRequest.of(page, 10);
		
		List<AC_TaxRate> taxList = this.taxRateRepository.findAll();
		
	    List<AC_WithholdingForm> calculateList = new ArrayList<>();
	    
	    for (HR_mem mem : memList) {
	        AC_WithholdingForm dto = new AC_WithholdingForm();
	        String name = mem.getName();
	        String depart = mem.getDeptName().getDeptName();
	        int pay = mem.getRegularPay() / 12;
	        int deductionAmount = 0; // 공제액 합계
	        List<Integer> calculateTax = new ArrayList<>();
	        
	        pay = pay - (pay % 10);
	        calculateTax.add(pay);
	        
	        for (AC_TaxRate tax : taxList) {
	            Double taxRate = tax.getTaxRate();
	            int taxAmount = (int) (pay * taxRate) - (int) (pay * taxRate) % 10;
	            calculateTax.add(taxAmount);
	            deductionAmount += taxAmount;
	        }
	        
	        calculateTax.add(deductionAmount);
	        calculateTax.add(pay - deductionAmount);
	        
	        dto.setName(name);
	        dto.setDepartName(depart);
	        dto.setResults(calculateTax);
	        
	        calculateList.add(dto);
	    }
	    
        int start = Math.toIntExact(pageable.getOffset());
        int end = (start + pageable.getPageSize()) > calculateList.size() ? calculateList.size() : (start + pageable.getPageSize());
        Page<AC_WithholdingForm> pageResult = new PageImpl<>(calculateList.subList(start, end), pageable, calculateList.size());
        
        return pageResult;
	}
}