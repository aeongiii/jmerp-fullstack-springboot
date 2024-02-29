package com.example.demo.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.AC_Bond;
import com.example.demo.Entity.AC_Debt;
import com.example.demo.Service.AC_BondService;
import com.example.demo.Service.AC_DebtService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequestMapping("/AC")
@RequiredArgsConstructor
@Controller
public class AC_BondDebtController {
	//채무,채권을 관리하는 웹사이트
	
	@Autowired
	private final AC_BondService bondService;
	
	@Autowired
	private final AC_DebtService debtService;
	
    @GetMapping("/bonddebt")
    public String List(Model model,
    		@RequestParam(value = "page", defaultValue ="0") int page,
    		@RequestParam(value = "keyword", required = false) String keyword, 
            @RequestParam(value = "category", required = false) String category,
            HttpServletRequest request) {
        
    	String currentUrl = request.getRequestURI();
		model.addAttribute("currentUrl", currentUrl);
		
    	Page<AC_Bond> bonds = this.bondService.getList(page);
        Page<AC_Debt> debts = this.debtService.getList(page);
		
	    if (category != null && !category.isEmpty()) {
	    	
	        if ("date".equals(category)) {
	            bonds = this.bondService.searchDateList(keyword, page);
	            debts = this.debtService.searchDateList(keyword, page);
	            
	        } else if ("trader".equals(category)) {
	        	bonds = this.bondService.searchTraderList(keyword, page);
	        	debts = this.debtService.searchTraderList(keyword, page);
	        	
	        } else if ("amount".equals(category)) {
	        	bonds = this.bondService.searchAmountList(keyword, page);
	        	debts = this.debtService.searchAmountList(keyword, page);
	        	
	        } else if ("maturityDate".equals(category)) {
	        	bonds = this.bondService.searchMaturityDateList(keyword, page);
	        	debts = this.debtService.searchMaturityDateList(keyword, page);
	        	
	        } else if ("description".equals(category)) {
	        	bonds = this.bondService.searchDescriptionList(keyword, page);
	        	debts = this.debtService.searchDescriptionList(keyword, page);
	        }
	    }
		
        model.addAttribute("bonds", bonds);
        model.addAttribute("debts", debts);
        
        return "ac/AC_bond_debt";
    }
    
    @GetMapping("/bondregi")
    public String bondRegiForm() {
    	
    	return "ac/AC_bond_regi";
    }
    
    @PostMapping("/bondregi")
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
    	
    	return "ac/AC_bond_regi";
    }
    
    @GetMapping("/debtregi")
    public String debtRegiForm() {
    	
    	return "ac/AC_debt_regi";
    }
    
    @PostMapping("/debtregi")
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
    	
    	return "ac/AC_debt_regi";
    }
    
    @PostMapping("/bondupdate")
    public String bondUpdate(
    		@RequestParam(name = "bondNumber") String bondNumber,
    		@RequestParam(name = "type") String type,
    		@RequestParam(name = "priceField") Double priceField,
    		@RequestParam(name = "amount") Double amount,
    		@RequestParam(name = "maturityDate", required = false) LocalDate maturityDate) {
    	
    	if (type.equals("할인")) {
    		
    		priceField = -priceField;
    	}
    	
    	List<String> data = this.bondService.getDescriptionByBondNumber(bondNumber);
    	
    	String description = "";
    	
    	if (!data.get(0).contains(String.valueOf(LocalDate.now()))) {
    	
    		description = "\n" + (LocalDate.now()) + ":";
    	
    	}
    	
    	if (priceField > 0) {
    		
    		String sIncrease = " 채권 금액 " + priceField + "원 추가";
    		
    		description = description + sIncrease;
    	}
    	
    	if (priceField < 0) {
    		
    		String sDecrease = " 채권 금액 " + -priceField + "원 할인"; 
    		
    		description = description + sDecrease;
    	}
    	
    	if (amount != 0) {
    		
    		String sAmount = " " + amount + "원 변제";
    		
    		description = description + sAmount;
    	}
    	
    	this.bondService.update(bondNumber, amount, priceField, maturityDate, description);
    	
    	return "redirect:/AC/bonddebt";
    }
    
	@PostMapping("/debtupdate")
	public String debtUpdate(
			@RequestParam(name = "debtNumber") String debtNumber,
			@RequestParam(name = "type") String type,
			@RequestParam(name = "priceField") Double priceField,
			@RequestParam(name = "amount") Double amount,
			@RequestParam(name = "maturityDate", required = false) LocalDate maturityDate) {
		
		if (type.equals("할인")) {
			
			priceField = -priceField;
		}

    	List<String> data = this.debtService.getDescriptionByDebtNumber(debtNumber);
    	
    	String description = "";
    	
    	if (!data.get(0).contains(String.valueOf(LocalDate.now()))) {
    	
    		description = "\n" + (LocalDate.now()) + ":";
    	
    	}

		if (priceField > 0) {
			
			String sIncrease = " 채권 금액 " + priceField + "원 추가";
			
			description = description + sIncrease;
		}
		
		if (priceField < 0) {
			
			String sDecrease = " 채권 금액 " + -priceField + "원 할인"; 
			
			description = description + sDecrease;
		}
		
		if (amount != 0) {
			
			String sAmount = " " + amount + "원 변제";
			
			description = description + sAmount;
		}
		
		this.debtService.update(debtNumber, amount, priceField, maturityDate, description);
		
		return "redirect:/AC/bonddebt";
	}
}