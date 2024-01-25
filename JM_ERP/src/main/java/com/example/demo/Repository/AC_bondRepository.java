package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.AC_bond;

@Repository
public interface AC_bondRepository extends JpaRepository<AC_bond, String>{

}
