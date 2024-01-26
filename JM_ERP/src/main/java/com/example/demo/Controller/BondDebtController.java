package com.example.demo.Controller;

import org.springframework.stereotype.Controller;

import com.example.demo.Service.AC_bondService;
import com.example.demo.Service.AC_debtService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BondDebtController {
	//채무,채권을 관리하는 웹사이트
	private final AC_bondService bondService;
	private final AC_debtService debtService;
}
