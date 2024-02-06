package com.example.demo.Service;

import org.springframework.stereotype.Service;

import com.example.demo.Repository.PD_RSRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PD_RSService {
	
	private final PD_RSRepository rsrepository;
}
