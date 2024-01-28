package com.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.AC_bond;
import com.example.demo.Repository.AC_bondRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AC_bondService {
	
	private final AC_bondRepository bondRepository;
	
    public List<AC_bond> getList() {
        return this.bondRepository.findAll();
    }
}
