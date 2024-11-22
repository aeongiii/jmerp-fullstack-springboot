package com.example.demo.jmerpfullstackspringboot2.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.jmerpfullstackspringboot2.entity.AC_DepositSlip;
import com.example.demo.jmerpfullstackspringboot2.entity.AC_SaleSlip;
import com.example.demo.jmerpfullstackspringboot2.repository.AC_DepositSlipRepository;
import com.example.demo.jmerpfullstackspringboot2.repository.SD_NBProductRepository;
import com.example.demo.jmerpfullstackspringboot2.repository.SD_PBProductRepository;
import com.example.demo.jmerpfullstackspringboot2.repository.SD_SellerRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AC_DepositSlipService {

    private final AC_DepositSlipRepository depositSlipRepository;
    private final SD_NBProductRepository NBProductRepository;
    private final SD_PBProductRepository PBProductRepository;
    private final SD_SellerRepository sellerRepository;

    Double commission = 0.1;

    public List<AC_DepositSlip> getList() {
        return this.depositSlipRepository.findAll();
    }

    public Page<AC_DepositSlip> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.depositSlipRepository.findAll(pageable);
    }

    public Page<AC_DepositSlip> searchTradeDateList(String keyword, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.depositSlipRepository.findByTradeDateContaining(keyword, pageable);
    }

    public Page<AC_DepositSlip> searchTraderList(String keyword, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.depositSlipRepository.findByTraderContaining(keyword, pageable);
    }

    public Page<AC_DepositSlip> searchDescriptionList(String keyword, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.depositSlipRepository.findByDescriptionContaining(keyword, pageable);
    }

    public Page<AC_DepositSlip> searchTransactionTypeList(String keyword, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.depositSlipRepository.findByTransactionTypeContaining(keyword, pageable);
    }

    public List<AC_DepositSlip> update(List<AC_SaleSlip> saleSlipList) { // 전표 갱신 시 마다 수익을 정산
//    	갱신시마다 각 판매 등록 업체로부터 팔린 갯 수 만큼 수익 정산
//    	판매수수료는 면세수익으로 표현 가능 과세물품, 면세물품

        List<AC_DepositSlip> updateSlipList = new ArrayList<AC_DepositSlip>();

        List<AC_DepositSlip> slipList = getList();

        int totalDescriptionEA = 0;

        for (AC_DepositSlip dSlip : slipList) {
            totalDescriptionEA += dSlip.getDescriptionEA();
        }

        String yearMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("yy" + "MM"));
        // depositSlip의 적요의 k 갯수 만큼 saleSlip의 전표를 패스하는 기능 추가해야됨

        int slipNum = 1;

        for (AC_DepositSlip dSlip : slipList) {
            if (dSlip.getSlipCode().substring(0, 4).equals(yearMonth)) {
                slipNum++;
            }
        }

        int i = slipNum; // 전표 코드 숫자에 영향을 줌

        for (String seller : this.sellerRepository.findAllSellerName()) {

            int j = 1; // saleSlip 전표의 갯수
            int k = 0; // 판매 건수 추적
            int revenue = 0; // 수익

            AC_DepositSlip slips = new AC_DepositSlip();

            for (AC_SaleSlip slip : saleSlipList) {

                if (totalDescriptionEA >= j) { // 전표의 갯수만큼 패스

                    j++;
                    continue;
                }

                if (seller.equals(this.NBProductRepository.findSellerNameByProductCode(slip.getProductCode()))
                        && !seller.equals("달토끼")) {
                    revenue += (slip.getAmount() * this.commission) - (slip.getAmount() * this.commission) % 10; // (5%, 1의 자리 절삭)
                    k++;
                }

                if (seller.equals(this.PBProductRepository.findSellerNameByProductCode(slip.getProductCode()))
                        && seller.equals("달토끼")) {
                    revenue += slip.getAmount();
                    k++;
                }
            }

            if (k == 0) {

                continue;
            }

            slips.setSlipCode(String.format("%sD%03d", yearMonth, i));
            slips.setTradeDate(LocalDate.now());
            slips.setTrader(seller);
            slips.setDescriptionEA(k);
            slips.setDescription("판매 건수 : " + k + "건");
            slips.setAmount(revenue);
            slips.setTransactionType("판매수수료");
            slips.setCreatedAt(LocalDateTime.now());

            if (seller.equals("달토끼")) {

                slips.setTransactionType("자사품 판매 수익");
            }

            updateSlipList.add(slips);

            i++;
        }
        return this.depositSlipRepository.saveAll(updateSlipList);
    }
}
