package com.example.demo.Controller;

import org.springframework.stereotype.Controller;

import com.example.demo.Service.AC_monthService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MonthController {
	//월소득 관리 기능을 담당하는 웹사이트
	private final AC_monthService monthService;
}
