package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Entity.SD_Purchase;

public interface SD_PurchaseRepository extends JpaRepository<SD_Purchase, Long> {
	
    Page<SD_Purchase> findAll(Pageable pageable);
}

