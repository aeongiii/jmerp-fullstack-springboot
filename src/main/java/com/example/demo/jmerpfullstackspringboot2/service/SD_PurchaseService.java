package com.example.demo.jmerpfullstackspringboot2.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.jmerpfullstackspringboot2.entity.SD_Purchase;
import com.example.demo.jmerpfullstackspringboot2.repository.SD_PurchaseRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SD_PurchaseService {

    private final SD_PurchaseRepository purchaseRepository;

    // 고객 모두 조회 (페이징)
    public Page<SD_Purchase> searchAll(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return purchaseRepository.findAll(pageable);
    }

    public List<SD_Purchase> getList() {
        return this.purchaseRepository.findAll();
    }

    // 고객 조회 로직 구현
    public Page<SD_Purchase> searchByMemberId(String memberId, Pageable pageable) {
        return purchaseRepository.findByMemberId(memberId, pageable);
    }

    // 고객 아이디로 구매내역 찾기
    public Page<SD_Purchase> findByMemberId(String memberId, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return purchaseRepository.findByMemberId(memberId, pageable);
    }

}