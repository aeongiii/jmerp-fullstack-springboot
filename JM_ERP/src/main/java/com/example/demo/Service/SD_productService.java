package com.example.demo.Service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.SD_PBproduct;
import com.example.demo.Entity.SD_Product;
import com.example.demo.Repository.SD_PBproductRepository;
import com.example.demo.Repository.SD_productRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service 
public class SD_productService {

	private final SD_productRepository productRepository;
	private final SD_PBproductRepository pbproductRepository;
	
	
//	public List<Product> search(){
//		return productRepository.findAll();
//	}
	
	public Page<SD_Product> searchAllproduct(int page){
		Pageable pageable = PageRequest.of(page, 10);
		return productRepository.findAll(pageable);
	}
	
	public Page<SD_PBproduct> searchPbproduct(int page){
		Pageable pageable = PageRequest.of(page, 10);
		return pbproductRepository.findAll(pageable);
	}
	

	
	//	public Optional<Product> detail(Long id){
//		return productRepository.findById(id);
//	}

}
