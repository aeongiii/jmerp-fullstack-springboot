package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.SD_Product;

public interface SD_productRepository extends JpaRepository<SD_Product, Long> {

	Page<SD_Product> findAll(Pageable pageable);
	
}
