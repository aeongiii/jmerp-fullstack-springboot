package com.example.demo.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Mg_AccountMG_Entity;
import com.example.demo.Repository.MG_accountmgRepository;

import jakarta.transaction.Transactional;
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
	
	public Optional<Mg_AccountMG_Entity> findaccountCode(Long accountCode){
		return mgaccountmgRepository.findById(accountCode);
	}
	
	public void save(Mg_AccountMG_Entity item) {
	    // 기존 물품 정보를 업데이트하기 위해 조회하지 않고, 직접 저장 메서드를 호출합니다.
		mgaccountmgRepository.save(item);
	}
	@Transactional
	public void deleteId(List<Long> id) {
		for(Long ids : id) {
			mgaccountmgRepository.deleteById(ids);
		}
	}
	
	public List<Mg_AccountMG_Entity> accountManager(){
		return this.mgaccountmgRepository.findAll();
	}
	
	
	
	public List<Mg_AccountMG_Entity> serchAccount(String accountName){
		return mgaccountmgRepository.findByaccountNameContaining(accountName);
	}
//	헤이 문제풀지말고 공부를 하라고;
}
