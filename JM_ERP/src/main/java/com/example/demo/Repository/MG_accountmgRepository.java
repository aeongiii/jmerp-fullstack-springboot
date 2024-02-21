package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Mg_AccountMG_Entity;

public interface MG_accountmgRepository extends JpaRepository<Mg_AccountMG_Entity, Long>{

	
	Page<Mg_AccountMG_Entity> findAll(Pageable pageable);
	
	List<Mg_AccountMG_Entity> findByaccountNameContaining(String accountName);
}
