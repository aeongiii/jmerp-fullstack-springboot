package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Entity.Mg_item_Regi;

public interface MG_itemRegiRepository extends JpaRepository<Mg_item_Regi,String>{
	
	List<Mg_item_Regi> findByItemCode(String itemcode);
}
