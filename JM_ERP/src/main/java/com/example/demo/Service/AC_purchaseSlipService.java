package com.example.demo.Service;

import org.springframework.stereotype.Service;

import com.example.demo.Repository.AC_purchaseSlipRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AC_purchaseSlipService {
	private final AC_purchaseSlipRepository purchaseSlipRepository;
}
