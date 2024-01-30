package com.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.AC_purchaseSlip;
import com.example.demo.Repository.AC_purchaseSlipRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AC_purchaseSlipService {
	
	private final AC_purchaseSlipRepository purchaseSlipRepository;
	
    public List<AC_purchaseSlip> getList() {
        return this.purchaseSlipRepository.findAll();
    }
}
