package com.example.demo.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.PD_WorkHistory;
import com.example.demo.Repository.PD_WorkHistoryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PD_WorkHistoryService {
	
	private final PD_WorkHistoryRepository whrepository;
	
	public List<PD_WorkHistory> getList(){
		return whrepository.findAll();
	}
	
	public Page<PD_WorkHistory> getList(int page){
		Pageable pageable = PageRequest.of(page, 10);
		return whrepository.findAll(pageable);
	}
}
