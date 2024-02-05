package com.example.demo.Controller;


import java.time.LocalDate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.Mg_WMS;
import com.example.demo.Entity.Mg_item_Regi;
import com.example.demo.Entity.Use_Self;
import com.example.demo.Service.MG_itemService;
import com.example.demo.Service.MG_useSelf;
import com.example.demo.Service.MG_wmsService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequestMapping("/MG")
@RequiredArgsConstructor
@Controller
public class MG_itemController {

	@Autowired
	private final MG_itemService MG_itemService;

	@GetMapping("/itemcheek")
	public String itemCheek(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
		Page<Mg_item_Regi> paging = this.MG_itemService.getList(page);
		model.addAttribute("paging", paging);

		return "MG_itemRegiCheck";
	}

	@GetMapping("/itemcreate")
	public String itemCreate() {
		return "MG_Item_form";
	}

	@PostMapping("/itemcreate")
	public String itemRegiCreate(@RequestParam(value = "itemName") String itemName,
			@RequestParam(value = "itemCode") String itemCode, @RequestParam(value = "itemCost") int itemCost,
			@RequestParam(value = "itemType") String itemType,
			@RequestParam(value = "itemReciving") LocalDate itemReciving) {

		MG_itemService.itemRegi(itemName, itemCode, itemCost, itemType, itemReciving);

		return "redirect:/MG/itemcheek";
	}

	@Autowired
	private final MG_wmsService MG_wmsService;

	@GetMapping("/relesecreate")
	public String wmsrelesecreate() {
		return "MG_wmsRelese_form";
	}

	@PostMapping("/relesecreate")
	public String wmsrelese(@RequestParam(value = "wareName") String wareName,
			@RequestParam(value = "wareLocation") String wareLocation,
			@RequestParam(value = "wareRelese") LocalDate wareRelese,
			@RequestParam(value = "itemCode") Mg_item_Regi itemCode,
			@RequestParam(value = "itemNumber") Integer itemNumber) {

		MG_wmsService.WMSRelese(wareName, wareLocation, wareRelese, itemCode.getItemname(), itemCode,
				itemCode.getItemCost(), itemCode.getItemType(), itemNumber);
		return "redirect:/MG/wmsrelese";
	}

	@GetMapping("/recivingcreate")
	public String wmsrecivingcreate() {
		return "MG_wmsReciving_form";
	}

	@PostMapping("/recivingcreate")
	public String wmsreciving(@RequestParam(value = "wareName") String wareName,
			@RequestParam(value = "wareLocation") String wareLocation,
			@RequestParam(value = "wareReciving") LocalDate wareReciving,
			@RequestParam(value = "itemCode") Mg_item_Regi itemCode,
			@RequestParam(value = "itemNumber") Integer itemNumber) {

		MG_wmsService.WMSreciving(wareName, wareLocation, wareReciving, itemCode.getItemname(), itemCode,
				itemCode.getItemCost(), itemCode.getItemType(), itemNumber);
		return "redirect:/MG/wmsrelese";
	}	
	@GetMapping("/wmsrelese")
	public String releserecivingCheck(Model model, @RequestParam(value = "page", defaultValue = "0") int page, HttpServletRequest request) {
		
		 String currentUrl = request.getRequestURI();
		    model.addAttribute("currentUrl", currentUrl);
		
		Page<Mg_WMS> paging1 = this.MG_wmsService.findRecordsByWareRelese(PageRequest.of(page, 10));
		model.addAttribute("paging1", paging1);
		
		Page<Mg_WMS> paging2 = this.MG_wmsService.findRecordsByWareReciving(PageRequest.of(page, 10));
		model.addAttribute("paging2", paging2);
		
		
		return "MG_wmsReleseCheck";
	}

	@PostMapping("/wmsrelese")
	public String releseCheck(Model model, @RequestParam(value = "nullDate", defaultValue="0") LocalDate nullDate ,@RequestParam(value = "page", defaultValue = "0") int page) {
		Page<Mg_WMS> paging2 = this.MG_wmsService.findRecordsByWareReciving(PageRequest.of(page, 10));
		model.addAttribute("paging2", paging2);
		
		return "MG_wmsReleseCheck";
	}
	
	
	
	private final  MG_useSelf MG_useSelf;
	
	@GetMapping("/useselfcreate")
	public String useselfcreate() {
		return "MG_useSelf_form";
	}
	@PostMapping("/useselfcreate")
	public String useselfCreate(
			@RequestParam(value = "useWareName") String useWareName,
			@RequestParam(value = "usName") String usName,
			@RequestParam(value = "useNum") Integer useNum,
			@RequestParam(value = "usDate") LocalDate usDate) {

		MG_useSelf.useSelf(useWareName,usDate, useNum, usName);

		return "redirect:/MG/useCheck";
	}
	
	@GetMapping("/useCheck")
	public String useCheck(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
		Page<Use_Self> paging = this.MG_useSelf.getList(page);
		model.addAttribute("paging", paging);
		
		return "MG_useSelfcheck";
	}
	
}
