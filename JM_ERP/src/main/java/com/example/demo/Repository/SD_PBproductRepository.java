package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Entity.SD_PBproduct;

public interface SD_PBproductRepository extends JpaRepository<SD_PBproduct, String> {
    Page<SD_PBproduct> findAll(Pageable pageable);
}
