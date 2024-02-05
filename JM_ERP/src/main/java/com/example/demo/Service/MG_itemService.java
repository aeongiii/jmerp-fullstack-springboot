package com.example.demo.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Mg_item_Regi;
import com.example.demo.Repository.MG_itemRegiRepository;

import lombok.RequiredArgsConstructor;

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
		
		public List<Mg_item_Regi> item(String itemcode){
			return this.MG_itemRegiRepository.findByItemCode(itemcode);
		}
		
		
		
		
	}
