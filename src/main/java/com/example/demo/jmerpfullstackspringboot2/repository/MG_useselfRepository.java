package com.example.demo.jmerpfullstackspringboot2.repository;

import com.example.demo.jmerpfullstackspringboot2.entity.Use_Self;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MG_useselfRepository extends JpaRepository<Use_Self, Integer> {


    List<Use_Self> findByuseCount(Integer useCount);
}
//