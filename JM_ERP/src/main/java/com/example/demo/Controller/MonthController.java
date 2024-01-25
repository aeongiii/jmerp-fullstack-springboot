package com.example.demo.Controller;

import org.springframework.stereotype.Controller;

import com.example.demo.Service.AC_monthService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MonthController {
	
	private final AC_monthService monthService;
}
