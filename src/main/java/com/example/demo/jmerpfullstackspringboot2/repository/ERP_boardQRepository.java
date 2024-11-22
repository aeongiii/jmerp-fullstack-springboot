package com.example.demo.jmerpfullstackspringboot2.repository;

import com.example.demo.jmerpfullstackspringboot2.entity.ERP_boardQ;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ERP_boardQRepository extends JpaRepository<ERP_boardQ, Integer> {

    Page<ERP_boardQ> findAll(Pageable pageable);
}
