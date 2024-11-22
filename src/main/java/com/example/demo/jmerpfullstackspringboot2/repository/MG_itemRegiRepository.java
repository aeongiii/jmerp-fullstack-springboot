package com.example.demo.jmerpfullstackspringboot2.repository;

import com.example.demo.jmerpfullstackspringboot2.entity.Mg_item_Regi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MG_itemRegiRepository extends JpaRepository<Mg_item_Regi, String> {


    Optional<Mg_item_Regi> findByItemCode(String itemCode);
}
