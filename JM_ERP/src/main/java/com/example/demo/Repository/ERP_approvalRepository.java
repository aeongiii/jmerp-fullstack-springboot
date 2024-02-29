package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.ERP_approval;

@Repository
public interface ERP_approvalRepository extends JpaRepository<ERP_approval, Integer>{
	
	Page<ERP_approval> findAll(Pageable pageable);
	
}
