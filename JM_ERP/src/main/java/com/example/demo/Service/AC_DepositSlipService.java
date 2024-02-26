package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.AC_DepositSlip;
import com.example.demo.Repository.AC_DepositSlipRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AC_DepositSlipService {
	
	private final AC_DepositSlipRepository depositSlipRepository;
	
    public List<AC_DepositSlip> getList() {
        return this.depositSlipRepository.findAll();
    }
    
    public Page<AC_DepositSlip> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.depositSlipRepository.findAll(pageable);
    }
    
    public List<AC_DepositSlip> save() {
    	return new ArrayList<AC_DepositSlip>();
    }
}
