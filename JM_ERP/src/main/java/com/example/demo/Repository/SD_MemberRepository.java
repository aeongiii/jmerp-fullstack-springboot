package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.SD_Member;

public interface SD_MemberRepository extends JpaRepository<SD_Member, String> {

	Page<SD_Member> findAll(Pageable pageable);
	
//	Page<SD_Member> findByName(String name,Pageable pageable);
	
	
	

}
