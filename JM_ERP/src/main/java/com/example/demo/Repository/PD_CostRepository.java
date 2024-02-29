package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.PD_Cost;

public interface PD_CostRepository extends JpaRepository<PD_Cost, String>{

	
	
	Optional<PD_Cost> findByprodNameContaining(String id);
}
