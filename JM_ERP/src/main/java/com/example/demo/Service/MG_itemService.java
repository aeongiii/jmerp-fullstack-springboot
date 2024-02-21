package com.example.demo.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Mg_item_Regi;
import com.example.demo.Repository.MG_itemRegiRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class MG_itemService {
		
		private final MG_itemRegiRepository MG_itemRegiRepository; 
		
//		물품 등록 조회
		public void itemRegi(String itemName, String itemCode, Integer itemCost, String itemType,
				LocalDate itemRegiDate) {
			Mg_item_Regi itemRegi = new Mg_item_Regi();
			
			itemRegi.setItemname(itemName);
			itemRegi.setItemCode(itemCode);
			itemRegi.setItemCost(itemCost);
			itemRegi.setItemReciving(itemRegiDate);
			itemRegi.setItemType(itemType);
			
			
			MG_itemRegiRepository.save(itemRegi);
		}

		public Page<Mg_item_Regi> getList(int page) {
			Pageable pageable = PageRequest.of(page, 10);
			return this.MG_itemRegiRepository.findAll(pageable);
		}

		public List<Mg_item_Regi> itemCheek() {
			return this.MG_itemRegiRepository.findAll();
		}
		
	
		public Optional<Mg_item_Regi> findItemCode(String itemCode) {
		    return MG_itemRegiRepository.findByItemCode(itemCode);
		}

		public void save(String itemName, Integer itemCost, String itemType) {
			Mg_item_Regi itemregi = new Mg_item_Regi();
			
			itemregi.setItemname(itemName);
			itemregi.setItemCost(itemCost);
			itemregi.setItemType(itemType);
			
			MG_itemRegiRepository.save(itemregi);
		}

		public void save(Mg_item_Regi item) {
		    // 기존 물품 정보를 업데이트하기 위해 조회하지 않고, 직접 저장 메서드를 호출합니다.
		    MG_itemRegiRepository.save(item);
		}
		
		@Transactional
		public void deleteId(List<String> id) {
			for(String ids : id) {
				MG_itemRegiRepository.deleteById(ids);
			}
		}
		
	}
