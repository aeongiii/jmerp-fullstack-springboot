package com.example.demo.jmerpfullstackspringboot2.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.jmerpfullstackspringboot2.entity.PD_BOM;
import com.example.demo.jmerpfullstackspringboot2.entity.PD_QC;
import com.example.demo.jmerpfullstackspringboot2.entity.PD_QCregi;
import com.example.demo.jmerpfullstackspringboot2.repository.PD_QCRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PD_QCService {

    private final PD_QCRepository qcrepository;

    public List<PD_QC> getList() {
        return qcrepository.findAll();
    }

    public Page<PD_QC> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return qcrepository.findAll(pageable);
    }

    public PD_QC create(int id, String qcTool, PD_BOM prodCode, int prodNum,
                        int qcNum, PD_QCregi qcList, String pF) {
        PD_QC qc = new PD_QC();
        qc.setId(id);
        qc.setQcTool(qcTool);
        qc.setProdCode(prodCode);
        qc.setProdNum(prodNum);
        qc.setQcNum(qcNum);
        qc.setQcList(qcList);
        qc.setPF(pF);
        this.qcrepository.save(qc);
        return qc;

    }
}
