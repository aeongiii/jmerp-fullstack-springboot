package com.example.demo.jmerpfullstackspringboot2.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.jmerpfullstackspringboot2.entity.PD_RS;
import com.example.demo.jmerpfullstackspringboot2.repository.PD_RSRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PD_RSService {

    private final PD_RSRepository rsrepository;

    public List<PD_RS> getList() {
        return rsrepository.findAll();
    }

    public Page<PD_RS> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return rsrepository.findAll(pageable);
    }
}
