package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.AC_Month;
import com.example.demo.Form.AC_WithholdingForm;
import com.example.demo.Service.AC_MonthService;
import com.example.demo.Service.AC_TaxRateService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequestMapping("/AC")
@RequiredArgsConstructor
@Controller
public class AC_FinanceController {
	//월소득 관리 기능을 담당하는 웹사이트
	
	@Autowired
	private final AC_MonthService monthService;
	
	@Autowired
	private final AC_TaxRateService taxRateService;
	
    @GetMapping("/month")
    public String monthList(Model model) {
        List<AC_Month> monthIncome = this.monthService.getList();
        model.addAttribute("monthIncome", monthIncome);
        return "ac/AC_month";
    }
    
    @GetMapping("/withholding") 
    public String withholdingList(Model model, @RequestParam(value = "page", defaultValue ="0") int page, HttpServletRequest request) {
    	
    	String currentUrl = request.getRequestURI();
		model.addAttribute("currentUrl", currentUrl);
    	
    	Page<AC_WithholdingForm> withholdings = this.taxRateService.calculateTax(page); //jpa를 이용하지 않았기 때문에 다른 paging을 이용해야 한다.
    	model.addAttribute("withholdings", withholdings);
    	
    	return "ac/AC_withholding_tax";
    }
}