package com.example.demo.Service;

import org.springframework.stereotype.Service;

import com.example.demo.Repository.AC_taxRateRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AC_taxRateService {
	
	private final AC_taxRateRepository taxRateRepository;
}
