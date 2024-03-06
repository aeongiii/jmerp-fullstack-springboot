package com.example.demo.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.AC_DepositSlip;
import com.example.demo.Entity.AC_Month;
import com.example.demo.Entity.AC_WithdrawalSlip;
import com.example.demo.Repository.AC_DepositSlipRepository;
import com.example.demo.Repository.AC_MonthRepository;
import com.example.demo.Repository.AC_WithdrawalSlipRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AC_MonthService {
	
	private final AC_MonthRepository monthRepository;
	private final AC_DepositSlipRepository depositRepository;
	private final AC_WithdrawalSlipRepository withdrawalRepository;
	
    public List<AC_Month> getList() {
        return this.monthRepository.findAll();
    }
    
    public AC_Month save(int year, int month) {
    	
    	String yyyy = String.valueOf(year);
    	String yy = String.valueOf(year).substring(2, 4);
    	String mm = String.format("%02d", month);
    	
    	String yearMonth = yy + mm;
    	
    	List<AC_DepositSlip> deposit = this.depositRepository.findBySlipCodeContaining(yearMonth);
    	List<AC_WithdrawalSlip> withdrawal = this.withdrawalRepository.findBySlipCodeContaining(yearMonth);
    	
    	if (deposit.isEmpty() || withdrawal.isEmpty()) {
    		
    		return null;
    	}
    	
    	int dAmount = 0;
    	int wAmount = 0;
    	int VAT = 0;
    	
    	for (AC_DepositSlip d : deposit) {
    		
    		dAmount += d.getAmount();
    		
    		if (d.getTrader().equals("달토끼")) {
    		
    			VAT += (d.getAmount() / 11) / 10 * 10;
    		}
    	}
    	
    	for (AC_WithdrawalSlip w : withdrawal) {
    		
    		wAmount += w.getAmount();
    	}
    	
    	AC_Month saveMonth = new AC_Month();
    	
    	saveMonth.setMonthId(yyyy + mm);
    	saveMonth.setRevenue(dAmount);
    	saveMonth.setVAT(VAT);
    	saveMonth.setExpense(wAmount);
    	saveMonth.setNetIncome(dAmount - VAT - wAmount);
    	saveMonth.setUpdatedAt(LocalDateTime.now());
    	
    	return this.monthRepository.save(saveMonth);
    }
    
    public AC_Month search(int year, int month) {
    	
    	String yyyy = String.valueOf(year);
    	String mm = String.format("%02d", month);
    	
    	String yearMonth = yyyy + mm;
    	
    	return this.monthRepository.findByMonthId(yearMonth);
    }
}
