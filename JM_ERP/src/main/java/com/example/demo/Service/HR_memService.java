package com.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.HR_mem;
import com.example.demo.Repository.HR_memRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HR_memService {
	
	private final HR_memRepository memRepository;
	
	public List<HR_mem> getList() {
		return this.memRepository.findAll();
	}
}
