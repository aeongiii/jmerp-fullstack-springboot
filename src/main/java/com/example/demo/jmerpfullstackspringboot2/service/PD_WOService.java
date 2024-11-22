package com.example.demo.jmerpfullstackspringboot2.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.jmerpfullstackspringboot2.entity.PD_BOM;
import com.example.demo.jmerpfullstackspringboot2.entity.PD_WO;
import com.example.demo.jmerpfullstackspringboot2.repository.PD_WORepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PD_WOService {

    private final PD_WORepository worepository;

    public List<PD_WO> getList() {
        return worepository.findAll();
    }

    public Page<PD_WO> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return worepository.findAll(pageable);
    }

    public PD_WO create(String orderNum, String deliveryName, String manager, int deliveryDate,
                        PD_BOM prodcode, int wOrder, int making, String factory) {
        PD_WO wo = new PD_WO();
        wo.setOrderNum(orderNum);
        wo.setDeliveryName(deliveryName);
        wo.setManager(manager);
        wo.setDeliveryDate(deliveryDate);
        wo.setProdCode(prodcode);
        wo.setWOrder(wOrder);
        wo.setMaking(making);
        wo.setFactory(factory);
        this.worepository.save(wo);
        return wo;


    }
}
