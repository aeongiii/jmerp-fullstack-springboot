package com.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.AC_debt;
import com.example.demo.Repository.AC_debtRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AC_debtService {
	private final AC_debtRepository debtRepository;
	
    public List<AC_debt> getList() {
        return this.debtRepository.findAll();
    }
}
