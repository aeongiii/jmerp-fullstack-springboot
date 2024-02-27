package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.SD_Sale;

public interface SD_SaleRepository extends JpaRepository<SD_Sale, Long>{
	
	Page<SD_Sale> findAll(Pageable pageable);
}
