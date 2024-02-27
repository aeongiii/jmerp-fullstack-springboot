package com.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.AC_Month;
import com.example.demo.Repository.AC_MonthRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AC_MonthService {
	
	private final AC_MonthRepository monthRepository;
	
    public List<AC_Month> getList() {
        return this.monthRepository.findAll();
    }
    
    //public List<> save() {
}
