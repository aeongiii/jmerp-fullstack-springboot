package com.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.Product;
import com.example.demo.Repository.productRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service 
public class productService {

	private final productRepository productRepository;
	
	public List<Product> search(){
		return productRepository.findAll();
	}
	
}
