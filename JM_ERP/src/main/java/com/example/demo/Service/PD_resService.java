package com.example.demo.Service;

import org.springframework.stereotype.Service;

import com.example.demo.Repository.PD_resRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PD_resService {
	
	private final PD_resRepository resrepository;
}
