package com.example.demo.Service;

import org.springframework.stereotype.Service;

import com.example.demo.Repository.PD_WorkHistoryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PD_WorkHistoryService {
	
	private final PD_WorkHistoryRepository whrepository;
}
