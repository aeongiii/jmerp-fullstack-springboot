package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.Entity.HR_mem;

public interface HR_memRepository extends JpaRepository<HR_mem, String>, JpaSpecificationExecutor<HR_mem>{

}
