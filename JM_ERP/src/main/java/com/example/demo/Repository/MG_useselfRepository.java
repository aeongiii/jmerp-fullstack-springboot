package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.Entity.Use_Self;

public interface MG_useselfRepository extends JpaRepository<Use_Self, Integer>{
	
	
	List<Use_Self> findByuseCount(Integer useCount);
}
//