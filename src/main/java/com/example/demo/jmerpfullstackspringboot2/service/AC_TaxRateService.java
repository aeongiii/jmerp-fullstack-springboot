package com.example.demo.jmerpfullstackspringboot2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.jmerpfullstackspringboot2.entity.AC_TaxRate;
import com.example.demo.jmerpfullstackspringboot2.entity.HR_dept;
import com.example.demo.jmerpfullstackspringboot2.entity.HR_mem;
import com.example.demo.jmerpfullstackspringboot2.form.AC_WithholdingForm;
import com.example.demo.jmerpfullstackspringboot2.repository.AC_TaxRateRepository;
import com.example.demo.jmerpfullstackspringboot2.repository.HR_memRepository;

import jakarta.persistence.criteria.Join;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AC_TaxRateService {

    private final AC_TaxRateRepository taxRateRepository;

    private final HR_memRepository memRepository;

    int nationalPension = 265500;

    public List<HR_mem> searchAll() {

        return this.memRepository.findAll();
    }

    public List<HR_mem> searchByName(String keyword) {
        Specification<HR_mem> spec = (root, query, cb) -> {
            return cb.like(root.get("name"), "%" + keyword + "%");
        };
        return memRepository.findAll(spec);
    }

    public List<HR_mem> searchByDeptName(String keyword) {
        Specification<HR_mem> spec = (root, query, cb) -> {

            Join<HR_mem, HR_dept> deptJoin = root.join("deptName");

            return cb.like(deptJoin.get("deptName"), "%" + keyword + "%");
        };
        return memRepository.findAll(spec);
    }

    public List<AC_WithholdingForm> calculateTaxList(List<HR_mem> memList) {

        List<AC_WithholdingForm> calculateList = new ArrayList<>();
        List<AC_TaxRate> taxList = this.taxRateRepository.findAll();

        for (HR_mem mem : memList) {
            AC_WithholdingForm dto = new AC_WithholdingForm();
            String name = mem.getName();
            String depart = mem.getDeptName().getDeptName();
            int pay = mem.getRegularPay() / 12;
            int deductionAmount = 0; // 공제액 합계
            List<Integer> calculateTax = new ArrayList<>();

            pay = pay - (pay % 10);
            calculateTax.add(pay);

            boolean count = true;

            for (AC_TaxRate tax : taxList) {
                Double taxRate = tax.getTaxRate();
                int taxAmount = (int) (pay * taxRate) - (int) (pay * taxRate) % 10;

                if (count) {
                    if (taxAmount > this.nationalPension) {
                        taxAmount = this.nationalPension;
                    }
                    count = false;
                }

                calculateTax.add(taxAmount);
                deductionAmount += taxAmount;
            }

            dto.setName(name);
            dto.setDepartName(depart);
            dto.setPay(calculateTax.get(0));
            dto.setNationalInsurance(calculateTax.get(1));
            dto.setHealthInsurance(calculateTax.get(2));
            dto.setLongCareInsurance(calculateTax.get(3));
            dto.setEmploymentInsurance(calculateTax.get(4));
            dto.setIncomeTax(calculateTax.get(5));
            dto.setDeduction(deductionAmount);
            dto.setNetIncome(pay - deductionAmount);
            calculateList.add(dto);
        }

        return calculateList;
    }

    public Page<AC_WithholdingForm> calculateTax(int page, List<HR_mem> memList) {
        Pageable pageable = PageRequest.of(page, 10);
        List<AC_WithholdingForm> calculateList = calculateTaxList(memList);

        int start = Math.toIntExact(pageable.getOffset());
        int end = (start + pageable.getPageSize()) > calculateList.size() ? calculateList.size() : (start + pageable.getPageSize());
        Page<AC_WithholdingForm> pageResult = new PageImpl<>(calculateList.subList(start, end), pageable, calculateList.size());

        return pageResult;
    }
}