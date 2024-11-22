package com.example.demo.jmerpfullstackspringboot2.controller;

import com.example.demo.jmerpfullstackspringboot2.entity.SD_NBProduct;
import com.example.demo.jmerpfullstackspringboot2.entity.SD_PBProduct;
import com.example.demo.jmerpfullstackspringboot2.entity.SD_Seller;
import com.example.demo.jmerpfullstackspringboot2.form.SD_NBProductCreateForm;
import com.example.demo.jmerpfullstackspringboot2.form.SD_PBProductCreateForm;
import com.example.demo.jmerpfullstackspringboot2.service.SD_NBProductService;
import com.example.demo.jmerpfullstackspringboot2.service.SD_PBProductService;
import com.example.demo.jmerpfullstackspringboot2.service.SD_SellerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/SD/product")
public class SD_ProductController {

    @Autowired
    private final SD_NBProductService nbService;
    private final SD_PBProductService pbService;
    private final SD_SellerService sellerService;

    // 판매자 상품조회
    @GetMapping("/nb")
    public String nb(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
                     @RequestParam(value = "sellerId", required = false) String sellerId, HttpServletRequest request) {

        String currentUrl = request.getRequestURI();
        model.addAttribute("currentUrl", currentUrl);

        // 모든 고객 정보 조회하여 모델에 추가
        List<SD_Seller> sellers = sellerService.findAllMembers();
        model.addAttribute("sellers", sellers);

        // 고객 id에 따라 해당 고객의 구매내역을 모델에 저장
        Page<SD_NBProduct> paging;
        if (sellerId != null && !sellerId.isEmpty()) {
            paging = nbService.findBySellerId(sellerId, page);
        } else {
            paging = nbService.searchAll(page);
        }

        model.addAttribute("nbProductList", paging.getContent());
        model.addAttribute("paging", paging);
        return "SD/SD_nb";

    }

    // 판매자 상품등록
    @GetMapping("/nbcreate")
    public String NBProductCreate(Model model) {
        List<SD_Seller> sellerList = sellerService.getSellerList();
        model.addAttribute("sellerList", sellerList);
        model.addAttribute("SD_NBProductCreateForm", new SD_NBProductCreateForm()); // nb상품 등록 폼을 모델에 추가
        return "SD/SD_NBProductCreate";
    }

    @PostMapping("/nbcreate")
    public String NBProductCreate(@Valid SD_NBProductCreateForm nbCreateForm, Model model) {
        nbService.saveNBProduct(nbCreateForm); // 상품코드를 form 및 DB에 저장
        return "redirect:/SD/product/nb";
    }


// ===============================================================================================================================

    // 자체상품 조회
    @GetMapping("/pb")
    public String pb(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
                     HttpServletRequest request) {

        String currentUrl = request.getRequestURI();
        model.addAttribute("currentUrl", currentUrl);

        // 자체상품 전체 가지고옴
        Page<SD_PBProduct> paging;
        paging = pbService.searchAll(page);

        model.addAttribute("pbProductList", paging.getContent());
        model.addAttribute("paging", paging);
        return "SD/SD_pb";

    }

    // 자체상품 등록
    @GetMapping("/pbcreate")
    public String PBProductCreate(Model model) {
        List<SD_Seller> sellerList = sellerService.getSellerList();
        model.addAttribute("sellerList", sellerList);
        model.addAttribute("SD_PBProductCreateForm", new SD_PBProductCreateForm()); // pb상품 등록 폼을 모델에 추가
        return "SD/SD_PBProductCreate";
    }

    @PostMapping("/pbcreate")
    public String PBProductCreate(@Valid SD_PBProductCreateForm pbCreateForm, Model model) {

//		pbCreateForm.setSellerId("000-00-00000");	// 직접 설정까지 했는데 왜 안들어갈까.. ㅠㅠ
//		pbCreateForm.setSellerName("달토끼");

        pbService.savePBProduct(pbCreateForm); // 상품코드를 form 및 DB에 저장

        return "redirect:/SD/product/pb";
    }
}
