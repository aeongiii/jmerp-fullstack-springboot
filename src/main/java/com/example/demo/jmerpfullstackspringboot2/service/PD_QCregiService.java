package com.example.demo.jmerpfullstackspringboot2.service;

import org.springframework.stereotype.Service;

import com.example.demo.jmerpfullstackspringboot2.repository.PD_QCregiRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PD_QCregiService {

    private final PD_QCregiRepository qcregirepository;
}
