package com.niqactivate.niqactivateapi.repository.interfaces;

import com.niqactivate.niqactivateapi.dto.ShopperDTO;
import com.niqactivate.niqactivateapi.entity.Shopper;
import com.niqactivate.niqactivateapi.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopperRepository extends JpaRepository<Shopper, String>, JpaSpecificationExecutor<Shopper>, CrudRepository<Shopper, String> {
    Page<Shopper> findAll(Specification<Shopper> spec, Pageable pageable);

    boolean existsByShopperIdAndProductId(String shopperId, String productId);
}


