package com.example.demo.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.Entity.AC_bond;
import com.example.demo.Entity.AC_debt;
import com.example.demo.Service.AC_bondService;
import com.example.demo.Service.AC_debtService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BondDebtController {
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
}
