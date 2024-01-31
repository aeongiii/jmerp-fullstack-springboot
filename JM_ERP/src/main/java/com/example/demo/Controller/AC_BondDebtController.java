package com.example.demo.Controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.AC_Bond;
import com.example.demo.Entity.AC_Debt;
import com.example.demo.Service.AC_BondService;
import com.example.demo.Service.AC_DebtService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AC_BondDebtController {
	//채무,채권을 관리하는 웹사이트
	
	@Autowired
	private final AC_BondService bondService;
	
	@Autowired
	private final AC_DebtService debtService;
	
    @GetMapping("/bond_debt")
    public String List(Model model, @RequestParam(value = "page", defaultValue ="0") int page, HttpServletRequest request) {
        
    	String currentUrl = request.getRequestURI();
		model.addAttribute("currentUrl", currentUrl);
		
    	Page<AC_Bond> bonds = this.bondService.getList(page);
        Page<AC_Debt> debts = this.debtService.getList(page);
        model.addAttribute("bonds", bonds);
        model.addAttribute("debts", debts);
        return "AC_bond_debt";
    }
    
    @GetMapping("/bond_regi")
    public String bondRegiForm() {
    	
    	return "AC_bond_regi";
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
    	
    	return "AC_bond_regi";
    }
    
    @GetMapping("/debt_regi")
    public String debtRegiForm() {
    	
    	return "AC_debt_regi";
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
    	
    	return "AC_debt_regi";
    }
    
    @GetMapping("/bond_update")
    public String bondUpdateForm() {
    	
    	return "AC_bond_update";
    }
    
    @GetMapping("/debt_update")
    public String debtUpdateForm() {
    	
    	return "AC_debt_update";
    }
}