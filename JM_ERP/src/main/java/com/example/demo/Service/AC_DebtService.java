package com.example.demo.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.AC_Debt;
import com.example.demo.Repository.AC_DebtRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AC_DebtService {
	private final AC_DebtRepository debtRepository;
	
    public List<AC_Debt> getList() {
        return this.debtRepository.findAll();
    }
    
    public void regi(String debtNumber,
    		LocalDate date,
    		String trader,
    		Double amount,
    		Double increaseDecreaseType,
    		Double balance,
    		LocalDate maturityDate,
    		String description) {
    	
    	AC_Debt debt = new AC_Debt();
    	
    	debt.setDebtNumber(debtNumber);
    	debt.setDate(date);
    	debt.setTrader(trader);
    	debt.setAmount(amount);
    	debt.setIncreaseDecreaseType(increaseDecreaseType);
    	debt.setBalance(balance);
    	debt.setMaturityDate(maturityDate);
    	debt.setDescription(description);
    	
    	this.debtRepository.save(debt);
    }
    
    public String generateDebtNumber() {
    	
        String latestDebtNumber = debtRepository.findMaxDebtNumber();
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        int number = 1; // 기본값 설정

        if (latestDebtNumber != null) {
            number = Integer.valueOf(latestDebtNumber.substring(3)) + 1;
        }

        return String.format("%sD%03d", year, number);
    }
    
    public Page<AC_Debt> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.debtRepository.findAll(pageable);
    }
}
