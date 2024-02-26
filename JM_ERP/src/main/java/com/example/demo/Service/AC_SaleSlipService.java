package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.AC_SaleSlip;
import com.example.demo.Repository.AC_SaleSlipRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AC_SaleSlipService {
	
	private final AC_SaleSlipRepository saleSlipRepository;
	
    public List<AC_SaleSlip> getList() {
        return this.saleSlipRepository.findAll();
    }
    
    public Page<AC_SaleSlip> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.saleSlipRepository.findAll(pageable);
    }
    
    public List<AC_SaleSlip> save() {
    	
    	List<AC_SaleSlip> list = new ArrayList<AC_SaleSlip>();
    	
    	
    	return new ArrayList<AC_SaleSlip>();
    }
}
