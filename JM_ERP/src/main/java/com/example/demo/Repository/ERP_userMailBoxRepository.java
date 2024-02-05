package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.ERP_user;
import com.example.demo.Entity.ERP_userMailBox;


public interface ERP_userMailBoxRepository extends JpaRepository<ERP_userMailBox, Long>{
	
	Page<ERP_userMailBox> findByReciveUser(ERP_user reciveUser,Pageable pageable);
}
