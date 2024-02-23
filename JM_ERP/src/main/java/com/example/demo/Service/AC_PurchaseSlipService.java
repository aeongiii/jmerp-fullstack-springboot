package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.AC_PurchaseSlip;
import com.example.demo.Repository.AC_PurchaseSlipRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AC_PurchaseSlipService {
	
	private final AC_PurchaseSlipRepository purchaseSlipRepository;
	
    public List<AC_PurchaseSlip> getList() {
        return this.purchaseSlipRepository.findAll();
    }
    
    public Page<AC_PurchaseSlip> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.purchaseSlipRepository.findAll(pageable);
    }
    
    public List<AC_PurchaseSlip> save() {
    	return new ArrayList<AC_PurchaseSlip>();
    }
}
