package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.ERP_boardA;

@Repository
public interface ERP_boardARepository extends JpaRepository<ERP_boardA, Integer>{

}
