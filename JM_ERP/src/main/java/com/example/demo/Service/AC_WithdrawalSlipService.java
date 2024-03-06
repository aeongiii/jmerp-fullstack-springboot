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
	
	private final AC_WithdrawalSlipRepository withdrawalSlipRepository;
	
    public List<AC_WithdrawalSlip> getList() {
        return this.withdrawalSlipRepository.findAll();
    }
    
    public Page<AC_WithdrawalSlip> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.withdrawalSlipRepository.findAll(pageable);
    }
    
    public Page<AC_WithdrawalSlip> searchTradeDateList(String keyword, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.withdrawalSlipRepository.findByTradeDateContaining(keyword, pageable);
    }
    
    public Page<AC_WithdrawalSlip> searchTraderList(String keyword, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.withdrawalSlipRepository.findByTraderContaining(keyword, pageable);
    }
    
	public Page<AC_WithdrawalSlip> searchDescriptionList(String keyword, int page) {
	    Pageable pageable = PageRequest.of(page, 10);
	    return this.withdrawalSlipRepository.findByDescriptionContaining(keyword, pageable);
	}
    
	public Page<AC_WithdrawalSlip> searchTransactionTypeList(String keyword, int page) {
	    Pageable pageable = PageRequest.of(page, 10);
	    return this.withdrawalSlipRepository.findByTransactionTypeContaining(keyword, pageable);
	}
}
