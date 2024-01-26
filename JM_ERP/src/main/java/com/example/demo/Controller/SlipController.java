package com.example.demo.Controller;

import org.springframework.stereotype.Controller;

import com.example.demo.Service.AC_purchaseSlipService;
import com.example.demo.Service.AC_saleSlipService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class SlipController {
	
	private final AC_saleSlipService saleSlipService;
	private final AC_purchaseSlipService purchaseSlipService;
}
