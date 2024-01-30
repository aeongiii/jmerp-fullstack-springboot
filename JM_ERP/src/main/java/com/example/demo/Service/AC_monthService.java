package com.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.AC_month;
import com.example.demo.Repository.AC_monthRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AC_monthService {
	
	private final AC_monthRepository monthRepository;
	
    public List<AC_month> getList() {
        return this.monthRepository.findAll();
    }
}
