package com.example.demo.Service;

import org.springframework.stereotype.Service;

import com.example.demo.Repository.AC_saleSlipRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AC_saleSlipService {
	
	private final AC_saleSlipRepository saleSlipRepository;

}
