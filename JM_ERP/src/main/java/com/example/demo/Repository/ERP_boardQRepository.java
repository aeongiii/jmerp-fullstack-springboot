package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.ERP_boardQ;

public interface ERP_boardQRepository extends JpaRepository<ERP_boardQ, Integer>{
	
	Page<ERP_boardQ> findAll(Pageable pageable);
}
