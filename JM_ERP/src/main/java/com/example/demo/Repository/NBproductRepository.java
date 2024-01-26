package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.NBProduct;

public interface NBproductRepository extends JpaRepository<NBProduct, Long> {

}
