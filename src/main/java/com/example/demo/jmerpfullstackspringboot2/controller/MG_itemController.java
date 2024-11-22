package com.example.demo.jmerpfullstackspringboot2.controller;

import com.example.demo.jmerpfullstackspringboot2.entity.Mg_WMS;
import com.example.demo.jmerpfullstackspringboot2.entity.Mg_item_Regi;
import com.example.demo.jmerpfullstackspringboot2.entity.Use_Self;
import com.example.demo.jmerpfullstackspringboot2.service.MG_itemService;
import com.example.demo.jmerpfullstackspringboot2.service.MG_useSelf;
import com.example.demo.jmerpfullstackspringboot2.service.MG_wmsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        return "MG/MG_itemRegiCheck";
    }

    @GetMapping("/itemcreate")
    public String itemCreate() {
        return "MG/MG_Item_form";
    }

    @PostMapping("/itemcreate")
    public String itemRegiCreate(@RequestParam(value = "itemName") String itemName,
                                 @RequestParam(value = "itemCode") String itemCode, @RequestParam(value = "itemCost") int itemCost,
                                 @RequestParam(value = "itemType") String itemType,
                                 @RequestParam(value = "itemReciving") LocalDate itemReciving) {

        MG_itemService.itemRegi(itemName, itemCode, itemCost, itemType, itemReciving);

        return "redirect:/MG/itemcheek";
    }

    @PostMapping("/itemcreate/deleteRegi")
    public String regitDelete(@RequestParam(name = "ids", defaultValue = "") String ids) {

        List<String> idsa = new ArrayList<>();
        for (String idstr : ids.split(",")) {
            try {
                // 빈 문자열을 체크하여 무시
                if (!idstr.trim().isEmpty()) {
                    idsa.add(idstr);
                }
            } catch (NumberFormatException e) {
                // 로그를 남기거나, 문제의 idstr 값을 포함한 오류 메시지 반환
                System.out.println("숫자로 변환할 수 없는 입력 발견: " + idstr);
                // 적절한 오류 처리를 여기에 수행
            }
        }

        // idsa를 사용한 비즈니스 로직 수행
        MG_itemService.deleteId(idsa);

        return "redirect:/MG/itemcheek";
    }

    @GetMapping("/itemupdate/{itemCode}")
    public String showUpdateForm(@PathVariable(name = "itemCode") String itemCode, Model model) {
        Optional<Mg_item_Regi> itemOptional = MG_itemService.findItemCode(itemCode);
        if (itemOptional.isPresent()) {
            model.addAttribute("item", itemOptional.get());
            return "MG/MG_Item_update"; // 해당 아이템을 수정할 수 있는 뷰 페이지
        } else {
            // 아이템이 존재하지 않는 경우의 처리
            return "redirect:/MG/itemcheek";
        }
    }

    @PostMapping("/itemupdate/{itemCode}")
    public String updateItem(@PathVariable("itemCode") String itemCode, @RequestParam("itemName") String itemName,
                             @RequestParam("itemCost") Integer itemCost, @RequestParam("itemType") String itemType,
                             RedirectAttributes redirectAttributes) {
        Optional<Mg_item_Regi> itemOptional = MG_itemService.findItemCode(itemCode);

        Mg_item_Regi item = itemOptional.get();
        item.setItemname(itemName);
        item.setItemCost(itemCost);
        item.setItemType(itemType);
        MG_itemService.save(item);
        redirectAttributes.addFlashAttribute("successMessage", "Item updated successfully!");
        return "redirect:/MG/itemcheek";

    }

//=================================================================================================	

    @Autowired
    private final MG_wmsService MG_wmsService;

    @GetMapping("/relesecreate")
    public String wmsrelesecreate() {
        return "MG/MG_wmsRelese_form";
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
        return "MG/MG_wmsReciving_form";
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
    public String releserecivingCheck(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
                                      HttpServletRequest request) {

        String currentUrl = request.getRequestURI();
        model.addAttribute("currentUrl", currentUrl);

        Page<Mg_WMS> paging1 = this.MG_wmsService.findRecordsByWareRelese(PageRequest.of(page, 10));
        model.addAttribute("paging1", paging1);

        Page<Mg_WMS> paging2 = this.MG_wmsService.findRecordsByWareReciving(PageRequest.of(page, 10));
        model.addAttribute("paging2", paging2);

        return "MG/MG_wmsReleseCheck";
    }

    @PostMapping("/wmsrelese")
    public String releseCheck(Model model, @RequestParam(value = "nullDate", defaultValue = "0") LocalDate nullDate,
                              @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Mg_WMS> paging2 = this.MG_wmsService.findRecordsByWareReciving(PageRequest.of(page, 10));
        model.addAttribute("paging2", paging2);

        return "MG/MG_wmsReleseCheck";
    }

//	========================================================================================

    private final MG_useSelf MG_useSelf;

    @GetMapping("/useselfcreate")
    public String useselfcreate() {
        return "MG/MG_useSelf_form";
    }

    @PostMapping("/useselfcreate")
    public String useselfCreate(@RequestParam(value = "useWareName") String useWareName,
                                @RequestParam(value = "usName") String usName, @RequestParam(value = "useNum") Integer useNum,
                                @RequestParam(value = "usDate") LocalDate usDate) {

        MG_useSelf.useSelf(useWareName, usDate, useNum, usName);

        return "redirect:/MG/useCheck";
    }

    @GetMapping("/useCheck")
    public String useCheck(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Use_Self> paging = this.MG_useSelf.getList(page);
        model.addAttribute("paging", paging);

        return "MG/MG_useSelfcheck";
    }

    @PostMapping("/useCheck/Delete")
    public String accountDelete(@RequestParam(name = "ids", defaultValue = "") Integer ids) {

        MG_useSelf.useDelete(ids);
        return "redirect:/MG/useCheck";
    }

}
