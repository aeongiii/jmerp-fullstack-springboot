package com.example.demo.jmerpfullstackspringboot2.controller;


import com.example.demo.jmerpfullstackspringboot2.entity.Mg_AccountMG_Entity;
import com.example.demo.jmerpfullstackspringboot2.service.MG_acoountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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


    @GetMapping("/accunntupdate/{accountCode}")
    public String showUpdateForm(@PathVariable(name = "accountCode") Long accountCode, Model model) {
        Optional<Mg_AccountMG_Entity> accountOp = mgser.findaccountCode(accountCode);
        if (accountOp.isPresent()) {
            model.addAttribute("item", accountOp.get());
            return "redirect:/MG/accmg"; // 해당 아이템을 수정할 수 있는 뷰 페이지
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
                             RedirectAttributes redirectAttributes,
                             Principal principal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (authorities.stream().anyMatch(auth -> "2".equals(auth.getAuthority()) || "3".equals(auth.getAuthority()))) {

            Optional<Mg_AccountMG_Entity> itemOptional = mgser.findaccountCode(Long.parseLong(accountCode));

            Mg_AccountMG_Entity item = itemOptional.get();
            item.setAccountName(accountName);
            item.setAccountNum(accountNum);
            item.setAccountManager(accountManager);

            mgser.save(item);

            return "redirect:/MG/accmg";
        } else return "MG/failupdatemodal";
    }

    @PostMapping("/accmg/DeleteAccount")
    public String accountDelete(@RequestParam(name = "ids", defaultValue = "") String ids) {
        if (ids.isEmpty()) {
            // 빈 문자열인 경우의 처리 로직
            // 예: 오류 메시지를 표시하거나, 특정 페이지로 리다이렉션
            return "hi";
        }

        List<Long> idsa = new ArrayList<>();
        for (String idstr : ids.split(",")) {
            try {
                // 빈 문자열을 체크하여 무시
                if (!idstr.trim().isEmpty()) {
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
