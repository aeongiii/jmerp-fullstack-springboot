package com.example.demo.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Mg_AccountMG_Entity;
import com.example.demo.Repository.MG_accountmgRepository;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MG_acoountService {
	
	private final MG_accountmgRepository mgaccountmgRepository;
	
	public void mgacRegi(String accName, String accNum, Long accCode, String accMg) {
		Mg_AccountMG_Entity mgen = new Mg_AccountMG_Entity();
		
		mgen.setAccountCode(accCode);
		mgen.setAccountManager(accMg);
		mgen.setAccountName(accName);
		mgen.setAccountNum(accNum);
		
		mgaccountmgRepository.save(mgen);
	}
	
	public Page<Mg_AccountMG_Entity> getList(int page){
		Pageable pageable = PageRequest.of(page, 10);
		return this.mgaccountmgRepository.findAll(pageable);
			
	
	}
	
	
	
	public List<Mg_AccountMG_Entity> accountCheek(){
		return mgaccountmgRepository.findAll();
	}
//	헤이 문제풀지말고 공부를 하라고;
}
