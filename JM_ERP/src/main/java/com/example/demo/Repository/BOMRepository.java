package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.BOM;

@Repository
public interface BOMRepository extends JpaRepository<BOM, String>{

}
