package com.example.demo.jmerpfullstackspringboot2.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.jmerpfullstackspringboot2.entity.PC_OrderSheet;
import com.example.demo.jmerpfullstackspringboot2.entity.PC_PurchaseInquiry;
import com.example.demo.jmerpfullstackspringboot2.repository.PC_OrderSheetRepository;
import com.example.demo.jmerpfullstackspringboot2.repository.PC_PurchaseInquiryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PC_PurchaseService {

    private final PC_OrderSheetRepository orderSheetRepository;
    private final PC_PurchaseInquiryRepository purchaseInquiryRepository;

    public List<PC_OrderSheet> getOrderSheetList() {
        return this.orderSheetRepository.findAll();
    }

    public Page<PC_OrderSheet> searchOrderSheet(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return orderSheetRepository.findAll(pageable);
    }

    public void orderSheetSave(String clinetName, String contactPerson, String item, LocalDate deliveryDate,
                               Double Count, String completionStatus, Long totalPrice, String bool, Long num, LocalDate PurchaseDate) {

        PC_OrderSheet order = new PC_OrderSheet();
        Optional<PC_PurchaseInquiry> purchase = findpurchase(num);

        purchase.get().setAcceptance(bool);

        order.setClientName(clinetName);
        order.setContactPerson(contactPerson);
        order.setItem(item);
        order.setDeliveryDate(deliveryDate);
        order.setCount(Count);
        order.setCompletionStatus(completionStatus);
        order.setTotalPrice(totalPrice);
        order.setPurchaseDate(PurchaseDate);

        orderSheetRepository.save(order);
        purchaseInquiryRepository.save(purchase.get());


    }

    public Page<PC_PurchaseInquiry> searchPurchaseInquiry(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return purchaseInquiryRepository.findAll(pageable);
    }

    public Optional<PC_PurchaseInquiry> findpurchase(Long id) {
        return purchaseInquiryRepository.findById(id);

    }

    public List<PC_PurchaseInquiry> findallpurchase() {
        return purchaseInquiryRepository.findAll();

    }


    public void purchaseSave(LocalDate PurchaseDate, String itemName, Double totalCount,
                             String warehouseName, String acceptance) {
        PC_PurchaseInquiry purchase = new PC_PurchaseInquiry();

        purchase.setPurchaseDate(PurchaseDate);
        purchase.setItemName(itemName);
        purchase.setTotalCount(totalCount);
        purchase.setWarehouseName(warehouseName);
        purchase.setAcceptance(acceptance);
        purchaseInquiryRepository.save(purchase);
    }

    public void purchaseClienteName(String clientName, Long num) {

        Optional<PC_PurchaseInquiry> purchase = findpurchase(num);
        purchase.get().setClientName(clientName);


        purchaseInquiryRepository.save(purchase.get());

    }


    public Optional<PC_OrderSheet> orderSheetfindId(Long id) {
        return orderSheetRepository.findById(id);
    }

    public void orderSheetupdate(Long id, Long orderNumber, LocalDate deliveryDate, String contactPerson, String clientName) {
        PC_OrderSheet orderSheet = orderSheetRepository.findById(id).get();
        PC_PurchaseInquiry purchase = purchaseInquiryRepository.findById(orderNumber).get();


        purchase.setClientName(clientName);
        orderSheet.setDeliveryDate(deliveryDate);
        orderSheet.setContactPerson(contactPerson);
        orderSheet.setClientName(clientName);
        orderSheetRepository.save(orderSheet);
        purchaseInquiryRepository.save(purchase);
    }


}
