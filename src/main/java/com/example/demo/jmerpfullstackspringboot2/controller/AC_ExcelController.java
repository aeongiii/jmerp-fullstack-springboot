package com.example.demo.jmerpfullstackspringboot2.controller;

import com.example.demo.jmerpfullstackspringboot2.service.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class AC_ExcelController {

    @Autowired
    private AC_ExcelService excelService;

    @Autowired
    private AC_BondService bondService;

    @Autowired
    private AC_DebtService debtService;

    @Autowired
    private AC_SaleSlipService saleSlipService;

    @Autowired
    private AC_WithdrawalSlipService withdrawalSlipService;

    @Autowired
    private AC_DepositSlipService depositSlipService;

    @Autowired
    private AC_TaxRateService taxRateService;

    @GetMapping("/export")
    public void exportToExcel(@RequestParam("buttonValue") String buttonValue, HttpServletResponse response) {

        List<?> entityList;

        switch (buttonValue) {
            case "bond":
                entityList = this.bondService.getList();
                break;
            case "debt":
                entityList = this.debtService.getList();
                break;
            case "deposit":
                entityList = this.depositSlipService.getList();
                break;
            case "withdrawal":
                entityList = this.withdrawalSlipService.getList();
                break;
            case "sale":
                entityList = this.saleSlipService.getList();
                break;
            case "withholding":
                entityList = this.taxRateService.calculateTaxList(this.taxRateService.searchAll());
                break;
            default:
                // 기본값 설정 또는 예외 처리
                entityList = Collections.emptyList();
        }
        excelService.exportToExcel(entityList, response);
    }
}
