package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

	Page<Member> findAll(Pageable pageable);
	
	

}
