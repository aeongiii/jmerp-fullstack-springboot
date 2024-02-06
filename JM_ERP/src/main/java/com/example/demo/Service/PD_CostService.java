package com.example.demo.Service;

import org.springframework.stereotype.Service;

import com.example.demo.Repository.PD_CostRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PD_CostService {

	
	private final PD_CostRepository costrepository;
}
