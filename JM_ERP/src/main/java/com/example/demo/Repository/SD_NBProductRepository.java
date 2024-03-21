package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.SD_NBProduct;

@Repository
public interface SD_NBProductRepository extends JpaRepository<SD_NBProduct, Long> {
	
    Page<SD_NBProduct> findAll(Pageable pageable);
    
 // 사업자등록번호에 따라 조회
    @Query("SELECT nb FROM SD_NBProduct nb WHERE nb.sellerId.sellerId = :sellerId")
    Page<SD_NBProduct> findBySellerId(@Param("sellerId")String sellerId, Pageable pageable);
    
// // 카테고리만 조회
//    @Query("SELECT nb FROM SD_NBProduct nb WHERE nb.category = :category")
//    List<SD_NBProduct> findByCategory(@Param("category") String category);

    // 회계에서 사용
    @Query("SELECT nb.sellerName FROM SD_NBProduct nb WHERE nb.productCode = :productCode")
    String findSellerNameByProductCode(@Param("productCode") String productCode);
    
    // 회계에서 사용
    @Query("SELECT nb.productName FROM SD_NBProduct nb WHERE nb.productCode = :productCode")
    String findProductNameByProductCode(@Param("productCode") String productCode);

//	SD_NBProduct findByProductCode(String productCode);
    Optional<SD_NBProduct> findByProductCode(String productCode);
    
	List<SD_NBProduct> findByCategory(String category);
	
	//주어진 productCode에 해당하는 SD_NBProduct의 카테고리를 조회
	@Query("SELECT nb.category FROM SD_NBProduct nb WHERE nb.productCode = :productCode")
	String findCategoryByProductCode(@Param("productCode") String productCode);
}
