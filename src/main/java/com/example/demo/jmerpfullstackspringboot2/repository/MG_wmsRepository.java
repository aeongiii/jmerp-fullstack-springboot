package com.example.demo.jmerpfullstackspringboot2.repository;

import com.example.demo.jmerpfullstackspringboot2.entity.Mg_WMS;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MG_wmsRepository extends JpaRepository<Mg_WMS, Integer> {


    Page<Mg_WMS> findByWareRecivingIsNotNull(Pageable pageable);

    Page<Mg_WMS> findByWareReleseIsNotNull(Pageable pageable);


}
