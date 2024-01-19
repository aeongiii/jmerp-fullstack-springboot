package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Seller;

public interface SellerRepository extends JpaRepository<Seller, String>{

}
