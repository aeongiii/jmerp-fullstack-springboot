package com.example.demo.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.AC_bond;
import com.example.demo.Entity.AC_debt;
import com.example.demo.Service.AC_bondService;
import com.example.demo.Service.AC_debtService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AC_BondDebtController {
	//채무,채권을 관리하는 웹사이트
	private final AC_bondService bondService;
	private final AC_debtService debtService;
	
    @GetMapping("/bond")
    public String bondList(Model model) {
        List<AC_bond> bonds = this.bondService.getList();
        model.addAttribute("bonds", bonds);
        return "bond";
    }
    
    @GetMapping("/debt")
    public String debtList(Model model) {
        List<AC_debt> debts = this.debtService.getList();
        model.addAttribute("debts", debts);
        return "debt";
    }
    
    @GetMapping("/bond_regi")
    public String bondRegiForm() {
    	
    	return "bond_regi";
    }
    
    @PostMapping("/bond_regi")
    public String regiBond(
    		 @RequestParam(name = "trader") String trader,
    		 @RequestParam(name = "amount") Double amount,
    		 @RequestParam(name = "maturityDate") LocalDate maturityDate,
    		 @RequestParam(name = "description") String description) {
    	
    	String bondNumber = this.bondService.generateBondNumber();
    	
    	LocalDate date = LocalDate.now();
    	
    	Double increaseDecreaseType = 0.0;
    	
    	Double balance = amount;
    	
    	this.bondService.regi(bondNumber, date, trader, amount, increaseDecreaseType, 
    			balance, maturityDate, description);
    	
    	return "bond_regi";
    }
    
    @GetMapping("/debt_regi")
    public String debtRegiForm() {
    	
    	return "debt_regi";
    }
    
    @PostMapping("/debt_regi")
    public String regiDebt(
    		 @RequestParam(name = "trader") String trader,
    		 @RequestParam(name = "amount") Double amount,
    		 @RequestParam(name = "maturityDate") LocalDate maturityDate,
    		 @RequestParam(name = "description") String description) {
    	
    	String debtNumber = this.debtService.generateDebtNumber();
    	
    	LocalDate date = LocalDate.now();
    	
    	Double increaseDecreaseType = 0.0;
    	
    	Double balance = amount;
    	
    	this.debtService.regi(debtNumber, date, trader, amount, increaseDecreaseType, 
    			balance, maturityDate, description);
    	
    	return "debt_regi";
    }
}
