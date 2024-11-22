package com.example.demo.jmerpfullstackspringboot2.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.jmerpfullstackspringboot2.entity.Use_Self;
import com.example.demo.jmerpfullstackspringboot2.repository.MG_useselfRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MG_useSelf {


    private final MG_useselfRepository MG_useselfRepository;

    public void useSelf(String useWareName, LocalDate usDate, Integer useNum, String usName) {
        Use_Self us = new Use_Self();

        us.setUsDate(usDate);
        us.setUseNum(useNum);
        us.setUseWareName(useWareName);
        us.setUsName(usName);

        MG_useselfRepository.save(us);
    }

    public Page<Use_Self> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.MG_useselfRepository.findAll(pageable);

    }

    public void useDelete(Integer accountcode) {

        MG_useselfRepository.deleteById(accountcode);
    }
}
		

