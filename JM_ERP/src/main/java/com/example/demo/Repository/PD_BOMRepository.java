package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.PD_BOM;

@Repository
public interface PD_BOMRepository extends JpaRepository<PD_BOM, String>{

	List<PD_BOM> findByProdCode(String prodCode);
}
