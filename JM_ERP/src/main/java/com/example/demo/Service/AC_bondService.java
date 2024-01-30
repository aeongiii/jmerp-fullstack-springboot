package com.example.demo.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.AC_bond;
import com.example.demo.Repository.AC_bondRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AC_bondService {
	
	private final AC_bondRepository bondRepository;
	
    public List<AC_bond> getList() {
        return this.bondRepository.findAll();
    }
    
    public void regi(String bondNumber,
    		LocalDate date,
    		String trader,
    		Double amount,
    		Double increaseDecreaseType,
    		Double balance,
    		LocalDate maturityDate,
    		String description) {
    	
    	AC_bond bond = new AC_bond();
    	
    	bond.setBondNumber(bondNumber);
    	bond.setDate(date);
    	bond.setTrader(trader);
    	bond.setAmount(amount);
    	bond.setIncreaseDecreaseType(increaseDecreaseType);
    	bond.setBalance(balance);
    	bond.setMaturityDate(maturityDate);
    	bond.setDescription(description);
    	
    	this.bondRepository.save(bond);
    }
    
    public String generateBondNumber() {
    	
        String latestBondNumber = bondRepository.findMaxBondNumber();
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        int number = 1; // 기본값 설정

        if (latestBondNumber != null) {
        	// 코드가 24B001 이런 식이므로 substring(3)을 하여 001 만 추출
            number = Integer.valueOf(latestBondNumber.substring(3)) + 1;
        }

        return String.format("%sB%03d", year, number);
    }
}
