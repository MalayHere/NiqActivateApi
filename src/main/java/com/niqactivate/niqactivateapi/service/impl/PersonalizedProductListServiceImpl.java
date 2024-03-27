package com.niqactivate.niqactivateapi.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.niqactivate.niqactivateapi.dto.PersonalizedProductList;
import com.niqactivate.niqactivateapi.entity.PersonalizedProduct;
import com.niqactivate.niqactivateapi.entity.ProductDetails;
import com.niqactivate.niqactivateapi.exception.DatabaseException;
import com.niqactivate.niqactivateapi.repository.interfaces.PersonalizedProductRepository;
import com.niqactivate.niqactivateapi.repository.interfaces.ProductDetailsRepository;
import com.niqactivate.niqactivateapi.service.interfaces.PersonalizedProductListService;
import com.niqactivate.niqactivateapi.utils.ShelfItem;
import jakarta.transaction.Transactional;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonalizedProductListServiceImpl implements PersonalizedProductListService {


    private final PersonalizedProductRepository personalizedProductRepository;
    private final ModelMapper modelMapper;

    public PersonalizedProductListServiceImpl(ModelMapper modelMapper, PersonalizedProductRepository personalizedProductListRepository) {
        this.modelMapper = modelMapper;
        this.personalizedProductRepository = personalizedProductListRepository;
    }

    @Override
    public Page<List<ProductDetails>> getProductsByShopper(String shopperId, String category, String brand, int limit) {
        if (limit <= 0 || limit > 100) {
            limit = 10;
        }
        return personalizedProductRepository.findProductsByShopperWithFilters(
                shopperId, category, brand, PageRequest.of(0, limit));
    }


    @Transactional
    public void savePersonalizedProducts(PersonalizedProductList personalizedProductList) {
        try {
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            personalizedProductRepository.saveAll(personalizedProductList.getShelfItemList()
                    .stream()
                    .map(shelfItem -> {
                        return new PersonalizedProduct(personalizedProductList.getShopperId(), shelfItem.getProductId(), shelfItem.getRelevancyScore());
                    })
                    .collect(Collectors.toList()));
        } catch (Exception ex) {
            throw new DatabaseException("Error occurred while saving products: " + ex.getMessage());
        }


    }
}