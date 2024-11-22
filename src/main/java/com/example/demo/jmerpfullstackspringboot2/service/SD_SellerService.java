
package com.example.demo.jmerpfullstackspringboot2.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.jmerpfullstackspringboot2.entity.SD_Seller;
import com.example.demo.jmerpfullstackspringboot2.form.SD_sellerCreateForm;
import com.example.demo.jmerpfullstackspringboot2.repository.SD_SellerRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SD_SellerService {

    private final SD_SellerRepository sellerRepository;

    // 판매자 등록 폼 저장
    public void saveSeller(@Valid SD_sellerCreateForm sellerCreateForm) {
        // 현재 날짜
        LocalDate today = LocalDate.now();

        SD_Seller newSeller = new SD_Seller();
        newSeller.setSellerAddress(sellerCreateForm.getSellerAddress()); // 컨트롤러에서 이미 생성된 사원번호 사용
        newSeller.setSellerEmail(sellerCreateForm.getSellerEmail());
        newSeller.setSellerId(sellerCreateForm.getSellerId());
        newSeller.setSellerJoinDate(today);
        newSeller.setSellerName(sellerCreateForm.getSellerName());
        newSeller.setSellerPhoneNumber(sellerCreateForm.getSellerPhoneNumber());

        sellerRepository.save(newSeller);

    }

    // 전체 판매자 조회
    public List<SD_Seller> findAllMembers() {
        return sellerRepository.findAll();
    }

    // 전체 판매자 조회 (페이징)
    public Page<SD_Seller> searchAll(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return sellerRepository.findAll(pageable);
    }

    // 전체 판매자 조회 (정렬)
    public List<SD_Seller> getSellerList() {
        List<SD_Seller> sellerList = sellerRepository.findAll();
        // 판매자명 기준으로 정렬 : Comparator.comparing 메서드 참조 방식 -> 'sellerList'의
        // 'SD_Seller'객체들을
        // 'sellerName' 필드값에 따라 오름차순 정렬
        sellerList.sort(Comparator.comparing(SD_Seller::getSellerName));
        return sellerList;
    }

    // "000-00-00000"을 제외한 모든 판매자 조회 (/search 에서 사용)
    public List<SD_Seller> findAllMembersExceptSelf() {
        return sellerRepository.findAllExceptSpecificSellerId(Pageable.unpaged()).getContent();
    }

    // "000-00-00000"을 제외한 모든 판매자 조회 (페이징) (/list 에서 사용)
    public Page<SD_Seller> findAllExceptSpecificSellerId(Pageable pageable) {
        return sellerRepository.findAllExceptSpecificSellerId(pageable);
    }

    // 입력받은 sellerID로 판매자 검색
    public SD_Seller findById(String sellerId) {
        return sellerRepository.findById(sellerId).orElse(null); // orElse(null)은 해당 ID를 가진 판매자가 없을 경우 null을 반환
    }

    // 판매자 이름으로 sellerId 찾기
    public String findSellerIdBySellerName(String sellerName) {
        SD_Seller seller = sellerRepository.findBySellerName(sellerName);    // 판매자 이름으로 판매자 정보 조회
        if (seller != null) {
            return seller.getSellerId();    // 판매자 존재할 경우 Id 반환
        } else {
            return null; // 또는 적절한 예외 처리
        }
    }


}