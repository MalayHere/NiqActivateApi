package com.niqactivate.niqactivateapi.service.interfaces;

import com.niqactivate.niqactivateapi.entity.Product;
import com.niqactivate.niqactivateapi.entity.Shopper;
import com.niqactivate.niqactivateapi.utils.PagedResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ShopperService {

//    public Page<List<Product>> getProductsByShopper(String shopperId, String category, String brand, int limit);


    PagedResponse<Shopper> findAll(Pageable pageable, Specification<Shopper> spec);

}
