package com.example.demo.jmerpfullstackspringboot2.controller;

import com.example.demo.jmerpfullstackspringboot2.entity.PC_OrderSheet;
import com.example.demo.jmerpfullstackspringboot2.entity.PC_PurchaseInquiry;
import com.example.demo.jmerpfullstackspringboot2.service.PC_PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("/PC/purchase")
@RestController
public class MGPC_RestController {

    @Autowired
    private PC_PurchaseService purchaseservice;

    @GetMapping("/ordersheetupdate")
    public ResponseEntity<PC_PurchaseInquiry> showUpdateForm(@RequestParam(name = "orderNumber") Long orderNumber) {
        Optional<PC_OrderSheet> orderOp = purchaseservice.orderSheetfindId(orderNumber);
        Optional<PC_PurchaseInquiry> purchaseInquiry = purchaseservice.findpurchase(orderNumber);

        if (purchaseInquiry.isPresent()) {
            return ResponseEntity.ok(purchaseInquiry.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
