package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.BOM;
import com.example.demo.Entity.PD_WorkHistory;

public interface PD_WorkHistoryRepository extends JpaRepository<PD_WorkHistory, BOM>{

}
