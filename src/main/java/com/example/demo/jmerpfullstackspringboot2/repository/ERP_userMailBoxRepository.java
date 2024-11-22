package com.example.demo.jmerpfullstackspringboot2.repository;

import com.example.demo.jmerpfullstackspringboot2.entity.ERP_user;
import com.example.demo.jmerpfullstackspringboot2.entity.ERP_userMailBox;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ERP_userMailBoxRepository extends JpaRepository<ERP_userMailBox, Long> {

    Page<ERP_userMailBox> findByReciveUser(ERP_user reciveUser, Pageable pageable);
}
