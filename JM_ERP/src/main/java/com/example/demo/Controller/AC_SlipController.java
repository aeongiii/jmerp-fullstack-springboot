package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.Entity.AC_purchaseSlip;
import com.example.demo.Entity.AC_saleSlip;
import com.example.demo.Service.AC_purchaseSlipService;
import com.example.demo.Service.AC_saleSlipService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AC_SlipController {
	//전표관리를 당담하는 웹사이트
	
	@Autowired
	private final AC_saleSlipService saleSlipService;
	
	@Autowired
	private final AC_purchaseSlipService purchaseSlipService;
	
    @GetMapping("/saleslip")
    public String saleList(Model model) {
        List<AC_saleSlip> saleSlipList = this.saleSlipService.getList();
        model.addAttribute("saleSlipList", saleSlipList);
        return "saleSlip";
    }
    
    @GetMapping("/purchaseslip")
    public String purchaseList(Model model) {
        List<AC_purchaseSlip> purchaseSlipList = this.purchaseSlipService.getList();
        model.addAttribute("purchaseSlipList", purchaseSlipList);
        return "purchaseSlip";
    }
}
