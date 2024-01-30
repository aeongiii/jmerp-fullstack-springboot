package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.Entity.AC_month;
import com.example.demo.Service.AC_monthService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AC_MonthController {
	//월소득 관리 기능을 담당하는 웹사이트
	
	@Autowired
	private final AC_monthService monthService;
	
    @GetMapping("/month")
    public String monthList(Model model) {
        List<AC_month> monthIncome = this.monthService.getList();
        model.addAttribute("monthIncome", monthIncome);
        return "month";
    }
}
