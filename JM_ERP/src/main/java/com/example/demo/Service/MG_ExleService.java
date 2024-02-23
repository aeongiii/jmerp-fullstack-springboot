package com.example.demo.Service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Mg_AccountMG_Entity;
import com.example.demo.Repository.MG_accountmgRepository;

@Service
public class MG_ExleService {
	
	@Autowired
	private MG_accountmgRepository mgResiRepository;
	
	
	
	public List<Mg_AccountMG_Entity> getaccountInfo(){
		  return mgResiRepository.findAll();
	}
	
	
	
	
}
