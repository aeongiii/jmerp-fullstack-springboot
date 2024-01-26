package com.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.AC_saleSlip;
import com.example.demo.Repository.AC_saleSlipRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AC_saleSlipService {
	
	private final AC_saleSlipRepository saleSlipRepository;
	
    public List<AC_saleSlip> getList() {
        return this.saleSlipRepository.findAll();
    }

}
