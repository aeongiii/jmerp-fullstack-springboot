
package com.example.demo.jmerpfullstackspringboot2.repository;

import com.example.demo.jmerpfullstackspringboot2.entity.SD_Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SD_MemberRepository extends JpaRepository<SD_Member, String> {

    Page<SD_Member> findAll(Pageable pageable);

//	Page<SD_Member> findByName(String name,Pageable pageable);


}