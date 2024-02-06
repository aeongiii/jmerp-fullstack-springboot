package com.example.demo.Service;

import org.springframework.stereotype.Service;

import com.example.demo.Repository.PD_QCregiRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PD_QCregiService {
	
	private final PD_QCregiRepository qcregirepository; 
}
