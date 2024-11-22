package com.example.demo.jmerpfullstackspringboot2.repository;

import com.example.demo.jmerpfullstackspringboot2.entity.PD_BOM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PD_BOMRepository extends JpaRepository<PD_BOM, String> {

    List<PD_BOM> findByProdCode(String prodCode);
}
