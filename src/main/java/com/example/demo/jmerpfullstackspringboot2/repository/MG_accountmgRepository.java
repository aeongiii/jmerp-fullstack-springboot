package com.example.demo.jmerpfullstackspringboot2.repository;

import com.example.demo.jmerpfullstackspringboot2.entity.Mg_AccountMG_Entity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MG_accountmgRepository extends JpaRepository<Mg_AccountMG_Entity, Long> {


    Page<Mg_AccountMG_Entity> findAll(Pageable pageable);

    List<Mg_AccountMG_Entity> findByaccountNameContaining(String accountName);

    List<Mg_AccountMG_Entity> findAll();
}
