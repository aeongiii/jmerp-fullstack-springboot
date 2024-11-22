package com.example.demo.jmerpfullstackspringboot2.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.jmerpfullstackspringboot2.entity.AC_Bond;
import com.example.demo.jmerpfullstackspringboot2.repository.AC_BondRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AC_BondService {

    private final AC_BondRepository bondRepository;

    public List<AC_Bond> getList() {
        return this.bondRepository.findAll();
    }

    public List<String> getDescriptionByBondNumber(String bondNumber) {
        return this.bondRepository.findDescriptionByBondNumber(bondNumber);
    }


    public void regi(String bondNumber,
                     LocalDate date,
                     String trader,
                     Double amount,
                     Double increaseDecreaseType,
                     Double balance,
                     LocalDate maturityDate,
                     String description) {

        AC_Bond bond = new AC_Bond();

        bond.setBondNumber(bondNumber);
        bond.setDate(date);
        bond.setTrader(trader);
        bond.setAmount(amount);
        bond.setIncreaseDecreaseType(increaseDecreaseType);
        bond.setBalance(balance);
        bond.setMaturityDate(maturityDate);
        bond.setDescription(description);

        this.bondRepository.save(bond);
    }

    public String generateBondNumber() {

        String latestBondNumber = bondRepository.findMaxBondNumber();
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        int number = 1; // 기본값 설정

        if (latestBondNumber != null) {
            // 코드가 24B001 이런 식이므로 substring(3)을 하여 001 만 추출
            number = Integer.valueOf(latestBondNumber.substring(3)) + 1;
        }

        return String.format("%sB%03d", year, number);
    }

    public Page<AC_Bond> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.bondRepository.findAll(pageable);
    }

    public void update(String bondNumber,
                       Double amount,
                       Double increaseDecreaseType,
                       LocalDate maturityDate,
                       String description) {

        Optional<AC_Bond> op = this.bondRepository.findById(bondNumber);
        AC_Bond bond = op.get();

        Double newBalance = bond.getBalance() + increaseDecreaseType - amount;

        if (newBalance < 0) {

            newBalance = 0.0;
        }

        if (maturityDate != null) {
            bond.setMaturityDate(maturityDate);
        }

        bond.setIncreaseDecreaseType(bond.getIncreaseDecreaseType() + increaseDecreaseType);
        bond.setBalance(newBalance);
        bond.setDescription(bond.getDescription() + description);

        this.bondRepository.save(bond);
    }

    public Page<AC_Bond> searchDateList(String keyword, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.bondRepository.findByDateContaining(keyword, pageable);
    }

    public Page<AC_Bond> searchTraderList(String keyword, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.bondRepository.findByTraderContaining(keyword, pageable);
    }

    public Page<AC_Bond> searchAmountList(String keyword, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.bondRepository.findByAmountContaining(keyword, pageable);
    }

    public Page<AC_Bond> searchMaturityDateList(String keyword, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.bondRepository.findByMaturityDateContaining(keyword, pageable);
    }

    public Page<AC_Bond> searchDescriptionList(String keyword, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.bondRepository.findByDescriptionContaining(keyword, pageable);
    }
}
