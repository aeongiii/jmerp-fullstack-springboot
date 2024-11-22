package com.example.demo.jmerpfullstackspringboot2.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.jmerpfullstackspringboot2.entity.AC_DepositSlip;
import com.example.demo.jmerpfullstackspringboot2.entity.AC_Month;
import com.example.demo.jmerpfullstackspringboot2.entity.AC_WithdrawalSlip;
import com.example.demo.jmerpfullstackspringboot2.entity.HR_mem;
import com.example.demo.jmerpfullstackspringboot2.repository.AC_DepositSlipRepository;
import com.example.demo.jmerpfullstackspringboot2.repository.AC_MonthRepository;
import com.example.demo.jmerpfullstackspringboot2.repository.AC_WithdrawalSlipRepository;
import com.example.demo.jmerpfullstackspringboot2.repository.HR_memRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AC_MonthService {

    private final AC_MonthRepository monthRepository;
    private final AC_DepositSlipRepository depositRepository;
    private final AC_WithdrawalSlipRepository withdrawalRepository;
    private final HR_memRepository memRepository;

    public List<AC_Month> getList() {
        return this.monthRepository.findAll();
    }

    public AC_Month save(int year, int month) {

        String yyyy = String.valueOf(year);
        String yy = String.valueOf(year).substring(2, 4);
        String mm = String.format("%02d", month);

        String yearMonth = yy + mm;

        List<AC_DepositSlip> deposit = this.depositRepository.findBySlipCodeContaining(yearMonth);
        List<AC_WithdrawalSlip> withdrawal = this.withdrawalRepository.findBySlipCodeContaining(yearMonth);

        List<HR_mem> memList = this.memRepository.findAll();

//    	if (deposit.isEmpty() || withdrawal.isEmpty()) {
//    		
//    		return null;
//    	}
//		위 를 사용한다면 입금, 출금 내역이 없을 때 아무런 데이터가 sql에 저장되지 않게 할 수 있음

        int revenue = 0;
        int PBRevenue = 0;
        int NBRevenue = 0;
        int expense = 0;
        int pay = 0;
        int VAT = 0;

        for (AC_DepositSlip d : deposit) {

            revenue += d.getAmount();

            if (d.getTrader().equals("달토끼")) {

                PBRevenue += d.getAmount();

                VAT += ((d.getAmount() / 11) / 10) * 10;
            } else {

                NBRevenue += d.getAmount();
            }
        }

        for (AC_WithdrawalSlip w : withdrawal) {

            expense += w.getAmount();
        }

        for (HR_mem mem : memList) {

            int memPay = mem.getRegularPay() / 12;
            memPay = memPay - (memPay % 10);

            pay += memPay;
        }

        AC_Month saveMonth = new AC_Month();

        saveMonth.setMonthId(yyyy + mm);
        saveMonth.setPBRevenue(PBRevenue);
        saveMonth.setNBRevenue(NBRevenue);
        saveMonth.setRevenue(revenue);
        saveMonth.setVAT(VAT);
        saveMonth.setExpense(expense);
        saveMonth.setNetIncome(revenue - VAT - expense - pay);
        saveMonth.setPay(pay);
        saveMonth.setUpdatedAt(LocalDateTime.now());

        return this.monthRepository.save(saveMonth);
    }

    public AC_Month search(int year, int month) {

        String yyyy = String.valueOf(year);
        String mm = String.format("%02d", month);

        String yearMonth = yyyy + mm;

        return this.monthRepository.findByMonthId(yearMonth);
    }
}
