package com.example.demo.jmerpfullstackspringboot2.controller;

import com.example.demo.jmerpfullstackspringboot2.entity.AC_Month;
import com.example.demo.jmerpfullstackspringboot2.form.AC_WithholdingForm;
import com.example.demo.jmerpfullstackspringboot2.service.AC_MonthService;
import com.example.demo.jmerpfullstackspringboot2.service.AC_TaxRateService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;

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
    public String monthList(Model model,
                            @RequestParam(value = "year", defaultValue = "0", required = false) Integer year,
                            @RequestParam(value = "month", defaultValue = "0", required = false) Integer month) {

        // 기본값 설정
        if (year == 0 || month == 0) {
            Calendar now = Calendar.getInstance();
            year = now.get(Calendar.YEAR);
            month = now.get(Calendar.MONTH) + 1; // 월은 0부터 시작하므로 +1 해줌
        }

        this.monthService.save(year, month);

        AC_Month monthIncome = this.monthService.search(year, month);
        model.addAttribute("monthIncome", monthIncome);
        return "ac/AC_month";
    }


    @GetMapping("/withholding")
    public String searchWithholding(Model model,
                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "keyword", required = false) String keyword,
                                    @RequestParam(value = "category", required = false) String category,
                                    HttpServletRequest request) {

        String currentUrl = request.getRequestURI();
        model.addAttribute("currentUrl", currentUrl);

        Page<AC_WithholdingForm> withholdings = null;

        if (category != null && !category.isEmpty()) {

            if ("name".equals(category)) {
                withholdings = this.taxRateService.calculateTax(page, this.taxRateService.searchByName(keyword));
            } else if ("department".equals(category)) {
                withholdings = this.taxRateService.calculateTax(page, this.taxRateService.searchByDeptName(keyword));
            }

        } else {

            withholdings = this.taxRateService.calculateTax(page, this.taxRateService.searchAll());
        }

        model.addAttribute("withholdings", withholdings);

        return "ac/AC_withholding_tax";
    }
}