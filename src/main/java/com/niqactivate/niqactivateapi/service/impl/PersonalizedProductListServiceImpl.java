package com.niqactivate.niqactivateapi.service.impl;


import com.niqactivate.niqactivateapi.dto.Product;
import com.niqactivate.niqactivateapi.entity.ProductDetails;
import com.niqactivate.niqactivateapi.repository.interfaces.PersonalizedProductRepository;
import com.niqactivate.niqactivateapi.service.interfaces.PersonalizedProductListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalizedProductListServiceImpl implements PersonalizedProductListService {

    @Autowired
    private PersonalizedProductRepository personalizedProductListRepository;


    @Override
    public Page<List<ProductDetails>> getProductsByShopper(String shopperId, String category, String brand, int limit) {
        if (limit <= 0 || limit > 100) {
            limit = 10; // Default limit
        }
        return personalizedProductListRepository.findProductsByShopperWithFilters(
                shopperId, category, brand, PageRequest.of(0, limit));
    }
}