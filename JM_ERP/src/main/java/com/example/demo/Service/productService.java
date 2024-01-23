package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Product;
import com.example.demo.Repository.productRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service 
public class productService {

	private final productRepository productRepository;
	
//	public List<Product> search(){
//		return productRepository.findAll();
//	}
	
	public Page<Product> search(int page){
		Pageable pageable = PageRequest.of(page, 10);
		return productRepository.findAll(pageable);
	}
	public Optional<Product> detail(Long id){
		return productRepository.findById(id);
	}

}
