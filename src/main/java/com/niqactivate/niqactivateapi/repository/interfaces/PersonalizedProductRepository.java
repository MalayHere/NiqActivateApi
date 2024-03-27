package com.niqactivate.niqactivateapi.repository.interfaces;

import com.niqactivate.niqactivateapi.entity.PersonalizedProduct;
import com.niqactivate.niqactivateapi.entity.ProductDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalizedProductRepository extends JpaRepository<PersonalizedProduct, String>{
    @Query("SELECT pdt FROM PersonalizedProduct p INNER JOIN ProductDetails pdt ON p.productId=pdt.productId WHERE p.shopperId=:shopperId AND (:category IS NULL OR pdt.category=:category) AND (:brand IS NULL OR pdt.brand=:brand)")
    Page<List<ProductDetails>> findProductsByShopperWithFilters(@Param("shopperId") String shopperId, @Param("category") String category, @Param("brand") String brand, Pageable pageable);
}


