package com.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.NBProduct;
import com.example.demo.Repository.NBproductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service 
public class NBproductService {

	private final NBproductRepository NBproductRepository;
	
	public List<NBProduct> search(){
		return NBproductRepository.findAll();
	}
	
}
