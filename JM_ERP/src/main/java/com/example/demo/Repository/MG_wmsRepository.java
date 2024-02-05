package com.example.demo.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Mg_WMS;

public interface MG_wmsRepository extends JpaRepository<Mg_WMS, Integer>{

	

	Page<Mg_WMS> findByWareRecivingIsNotNull(Pageable pageable);
	
	Page<Mg_WMS> findByWareReleseIsNotNull(Pageable pageable);
	
	
}
