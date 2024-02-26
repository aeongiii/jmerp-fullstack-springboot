package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.SD_NBProduct;

public interface SD_NBProductRepository extends JpaRepository<SD_NBProduct, Long> {
	
    Page<SD_NBProduct> findAll(Pageable pageable);
}
