package com.example.demo.Service;

import org.springframework.stereotype.Service;

import com.example.demo.Repository.PD_QCRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PD_QCService {
	
	private final PD_QCRepository qcrepository;
}
