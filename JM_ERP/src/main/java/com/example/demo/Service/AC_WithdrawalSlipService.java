package com.example.demo.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.AC_WithdrawalSlip;
import com.example.demo.Repository.AC_WithdrawalSlipRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AC_WithdrawalSlipService {
	
	private final AC_WithdrawalSlipRepository WithdrawalSlipRepository;
	
    public List<AC_WithdrawalSlip> getList() {
        return this.WithdrawalSlipRepository.findAll();
    }
    
    public Page<AC_WithdrawalSlip> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.WithdrawalSlipRepository.findAll(pageable);
    }
}
