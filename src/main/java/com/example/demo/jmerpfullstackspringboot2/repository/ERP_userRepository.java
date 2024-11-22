package com.example.demo.jmerpfullstackspringboot2.repository;

import com.example.demo.jmerpfullstackspringboot2.entity.ERP_user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ERP_userRepository extends JpaRepository<ERP_user, Long> {

    Optional<ERP_user> findByUserId(String userId);
}
