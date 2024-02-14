package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.AC_TaxRate;
import com.example.demo.Entity.HR_mem;
import com.example.demo.Form.AC_WithholdingForm;
import com.example.demo.Repository.AC_TaxRateRepository;
import com.example.demo.Repository.HR_memRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AC_TaxRateService {
	
	private final AC_TaxRateRepository taxRateRepository;
	private final HR_memRepository memRepository;
	
    public Page<AC_WithholdingForm> calculateTax(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
		
		List<HR_mem> memList = this.memRepository.findAll();
		
		List<AC_TaxRate> taxList = this.taxRateRepository.findAll();
		
	    List<AC_WithholdingForm> calculateList = new ArrayList<>();
	    
	    for (HR_mem mem : memList) {
	        AC_WithholdingForm dto = new AC_WithholdingForm();
	        String name = mem.getName();
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
	        dto.setResults(calculateTax);
	        
	        calculateList.add(dto);
	    }
	    
	    return new PageImpl<>(calculateList, pageable, calculateList.size());
	}
}