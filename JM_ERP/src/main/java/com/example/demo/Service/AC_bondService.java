package com.example.demo.Service;

import org.springframework.stereotype.Service;

import com.example.demo.Repository.AC_bondRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AC_bondService {
	
	private final AC_bondRepository bondRepository;
}
