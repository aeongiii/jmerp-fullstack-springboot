package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Entity.AC_Month;
import com.example.demo.Service.AC_MonthService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/AC")
@RequiredArgsConstructor
@Controller
public class AC_FinanceController {
	//월소득 관리 기능을 담당하는 웹사이트
	
	@Autowired
	private final AC_MonthService monthService;
	
    @GetMapping("/month")
    public String monthList(Model model) {
        List<AC_Month> monthIncome = this.monthService.getList();
        model.addAttribute("monthIncome", monthIncome);
        return "ac/AC_month";
    }
    
    @GetMapping("/withholding") 
    public String withholdingList() {
    	
    	return "ac/AC_withholding_tax";
    }
}