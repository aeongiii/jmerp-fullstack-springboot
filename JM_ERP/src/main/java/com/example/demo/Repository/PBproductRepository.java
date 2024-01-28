package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Entity.PBproduct;

public interface PBproductRepository extends JpaRepository<PBproduct, String> {
    Page<PBproduct> findAll(Pageable pageable);
}
