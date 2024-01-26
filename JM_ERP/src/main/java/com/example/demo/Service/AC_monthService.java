package com.example.demo.Service;

import org.springframework.stereotype.Service;

import com.example.demo.Repository.AC_monthRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AC_monthService {
	
	private final AC_monthRepository monthRepository;
}
