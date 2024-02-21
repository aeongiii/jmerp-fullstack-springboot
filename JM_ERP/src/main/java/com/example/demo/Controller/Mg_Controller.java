package com.example.demo.Controller;

import java.util.*;
import java.util.List;
import java.util.Optional;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Entity.HR_mem;
import com.example.demo.Entity.Mg_AccountMG_Entity;

import com.example.demo.Service.MG_acoountService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/MG")
@RequiredArgsConstructor
@Controller
public class Mg_Controller {

	@Autowired
	private final MG_acoountService mgser;

	
	@GetMapping("/accmg")
	public String mgcheck(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
		Page<Mg_AccountMG_Entity> paging = this.mgser.getList(page);
		model.addAttribute("paging", paging);

		return "MG/MG_acccheck";
	}

	@GetMapping("/accmgcreate")
	public String mgaccregi() {
		return "MG/MG_accmg_form";
	}

	@PostMapping("/accmgcreate")
	public String accountCheek(
			@RequestParam(value = "accountCode") Long accountCode,
			@RequestParam(value = "accountNum") String accountNum,
			@RequestParam(value = "accountName") String accountName,
			@RequestParam(value = "accountManager") String accountManager) {

		mgser.mgacRegi(accountName, accountNum, accountCode, accountManager);

		return "redirect:/MG/accmg";
	}
	
//	@GetMapping("/accmg")
//	public String serchAccount(@RequestParam(value="search",required=false)String search,Model model) {
//		List<Mg_AccountMG_Entity> accounts;
//		
//		if(search != null && !search.isEmpty()) {
//			accounts = mgser.serchAccount(search);
//	}else {
//		accounts = mgser.accountCheek();
//	}
//		model.addAttribute("accounts",accounts);
//	return "MG/MG_acccheck";
//}

	

	
	@GetMapping("/accunntupdate/{accountCode}")
	public String showUpdateForm(@PathVariable(name = "accountCode") Long accountCode, Model model) {
	    Optional<Mg_AccountMG_Entity> accountOp = mgser.findaccountCode(accountCode);
	    if (accountOp.isPresent()) {
	        model.addAttribute("item", accountOp.get());
	        return "MG/accmg"; // 해당 아이템을 수정할 수 있는 뷰 페이지
	    } else {
	        // 아이템이 존재하지 않는 경우의 처리
	        return "redirect:/MG/MG_acccheck";
	    }
	}
	
	
	
	
	
	@PostMapping("/accunntupdate/{accountCode}")
	public String updateItem(@PathVariable("accountCode") String accountCode,
	                         @RequestParam("accountName") String accountName,
	                         @RequestParam("accountNum") String accountNum,
	                         @RequestParam("accountManager") String accountManager,
	                         RedirectAttributes redirectAttributes) {
	    Optional<Mg_AccountMG_Entity> itemOptional = mgser.findaccountCode(Long.parseLong(accountCode));
	  
	    Mg_AccountMG_Entity item = itemOptional.get();
	        item.setAccountName(accountName);
	        item.setAccountNum(accountNum);
	        item.setAccountManager(accountManager);
	        
	        mgser.save(item);
	        
	        return "redirect:/MG/accmg";
	
	}
	
	@PostMapping("/accmg/DeleteAccount")
	public String accountDelete(@RequestParam(name = "ids",defaultValue = "") String ids) {
	    if(ids.isEmpty()) {
	        // 빈 문자열인 경우의 처리 로직
	        // 예: 오류 메시지를 표시하거나, 특정 페이지로 리다이렉션
	        return "hi";
	    }
	    
	    List<Long> idsa = new ArrayList<>();
	    for(String idstr : ids.split(",")) {
	        try {
	            // 빈 문자열을 체크하여 무시
	            if(!idstr.trim().isEmpty()) {
	                idsa.add(Long.parseLong(idstr.trim()));
	            }
	        } catch (NumberFormatException e) {
	            // 로그를 남기거나, 문제의 idstr 값을 포함한 오류 메시지 반환
	            System.out.println("숫자로 변환할 수 없는 입력 발견: " + idstr);
	            // 적절한 오류 처리를 여기에 수행
	        }
	    }
	    
	    // idsa를 사용한 비즈니스 로직 수행
	    mgser.deleteId(idsa);
	    
	    return "redirect:/MG/accmg";
	}

}
