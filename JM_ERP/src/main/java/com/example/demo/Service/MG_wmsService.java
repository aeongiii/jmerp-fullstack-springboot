package com.example.demo.Service;

import java.time.LocalDate;


import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Mg_WMS;
import com.example.demo.Entity.Mg_item_Regi;
import com.example.demo.Repository.MG_wmsRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MG_wmsService {

	private final MG_wmsRepository MG_wmsRepository;
	
	
	
	public void WMSreciving( String wareName, String wareLocation,
			LocalDate wareReciving, String itemName, Mg_item_Regi itemCode, Integer itemCost, String itemType,
			Integer itemNumber) {
		Mg_WMS wms = new Mg_WMS();
		
		wms.setWareName(wareName);
		wms.setWareLocation(wareLocation);
		wms.setWareReciving(wareReciving);
		wms.setItemName(itemName);
		wms.setItemCode(itemCode);
		wms.setItemCost(itemCost);
		wms.setItemType(itemType);
		wms.setItemNumber(itemNumber);
		MG_wmsRepository.save(wms);
		

	}

	public void WMSRelese( String wareName, String wareLocation,
			LocalDate wareRelese, String itemName, Mg_item_Regi itemCode, Integer itemCost, String itemType,
			Integer itemNumber) {
		Mg_WMS wms = new Mg_WMS();


		wms.setWareName(wareName);
		wms.setWareLocation(wareLocation);
		wms.setWareRelese(wareRelese);
		wms.setItemName(itemName);
		wms.setItemCode(itemCode);
		wms.setItemCost(itemCost);
		wms.setItemType(itemType);
		wms.setItemNumber(itemNumber);

		MG_wmsRepository.save(wms);
	}

	public Page<Mg_WMS> findRecordsByWareRelese(Pageable pageable){
		return MG_wmsRepository.findByWareReleseIsNotNull(pageable);
	}
	
	public Page<Mg_WMS> findRecordsByWareReciving(Pageable pageable){
		return MG_wmsRepository.findByWareRecivingIsNotNull(pageable);
	}
}
