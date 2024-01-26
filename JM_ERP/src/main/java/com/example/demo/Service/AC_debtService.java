package com.example.demo.Service;

import org.springframework.stereotype.Service;

import com.example.demo.Repository.AC_debtRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AC_debtService {
	private final AC_debtRepository debtRepository;
}
