package com.example.demo.jmerpfullstackspringboot2.service;

import org.springframework.stereotype.Service;

import com.example.demo.jmerpfullstackspringboot2.repository.PD_resRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PD_resService {

    private final PD_resRepository resrepository;
}
