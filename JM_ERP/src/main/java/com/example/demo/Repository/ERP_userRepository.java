package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.ERP_user;

public interface ERP_userRepository extends JpaRepository<ERP_user, Long>{

	Optional<ERP_user> findByuserId(String userId);
}
