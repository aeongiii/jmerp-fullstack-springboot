package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Product;

public interface productRepository extends JpaRepository<Product, Long> {

	Page<Product> findAll(Pageable pageable);
	
}
