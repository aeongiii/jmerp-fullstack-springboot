
package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.SD_PBProduct;

public interface SD_PBProductRepository extends JpaRepository<SD_PBProduct, Long> {
	
    Page<SD_PBProduct> findAll(Pageable pageable);


}
