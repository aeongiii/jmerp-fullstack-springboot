package com.example.demo.jmerpfullstackspringboot2.repository;

import com.example.demo.jmerpfullstackspringboot2.entity.PD_Cost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PD_CostRepository extends JpaRepository<PD_Cost, String> {


    Optional<PD_Cost> findByprodNameContaining(String id);
}
