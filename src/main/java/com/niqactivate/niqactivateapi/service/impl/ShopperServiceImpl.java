package com.niqactivate.niqactivateapi.service.impl;

import com.niqactivate.niqactivateapi.dto.ShopperDTO;
import com.niqactivate.niqactivateapi.entity.Shopper;
import com.niqactivate.niqactivateapi.exception.DatabaseException;
import com.niqactivate.niqactivateapi.exception.ProductNotFoundException;
import com.niqactivate.niqactivateapi.exception.ShopperProductCombinationExistsException;
import com.niqactivate.niqactivateapi.repository.interfaces.ProductDetailsRepository;
import com.niqactivate.niqactivateapi.repository.interfaces.ShopperRepository;
import com.niqactivate.niqactivateapi.service.interfaces.ShopperService;
import com.niqactivate.niqactivateapi.utils.PagedResponse;
import jakarta.transaction.Transactional;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class ShopperServiceImpl implements ShopperService {


    @Autowired
    private final ShopperRepository shopperRepository;
    private final ModelMapper modelMapper;
    @Autowired
    private ProductDetailsRepository productDetailsRepository;

    public ShopperServiceImpl(ModelMapper modelMapper, ShopperRepository personalizedProductListRepository) {
        this.modelMapper = modelMapper;
        this.shopperRepository = personalizedProductListRepository;
    }

    @Override
    public PagedResponse<Shopper> findAll(Pageable pageable, Specification<Shopper> spec) {

        Page<Shopper> data = shopperRepository.findAll(spec, pageable);
        if(!data.hasContent())
            return new PagedResponse<Shopper>(Collections.emptyList(), data.getNumber(),
                    data.getSize(), data.getTotalElements(), data.getTotalPages(),
                    data.isLast());

        return new PagedResponse<Shopper>(data.getContent(), data.getNumber(),
                data.getSize(), data.getTotalElements(), data.getTotalPages(),
                data.isLast());
    }


    @Transactional
    public void savePersonalizedProducts(ShopperDTO shopperDTO) {
        try {
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            shopperDTO.getShelfItemList().forEach(shelfItem -> {
                if (!productDetailsRepository.existsById(shelfItem.getProductId())) {
                    throw new ProductNotFoundException("Product ID " + shelfItem.getProductId() + " does not exist.");
                }
            });
            shopperDTO.getShelfItemList().forEach(shelfItem -> {
                if (shopperRepository.existsByShopperIdAndProductId(shopperDTO.getShopperId(), shelfItem.getProductId())) {
                    throw new ShopperProductCombinationExistsException("The combination of shopper " + shopperDTO.getShopperId() + " and product " + shelfItem.getProductId() + " already exists.");
                }
            });
            shopperRepository.saveAll(shopperDTO.getShelfItemList()
                    .stream()
                    .map(shelfItem -> new Shopper(shopperDTO.getShopperId(), shelfItem.getProductId(), shelfItem.getRelevancyScore()))
                    .collect(Collectors.toList()));
        } catch (ProductNotFoundException | ShopperProductCombinationExistsException ex) {
            throw ex; // Rethrow the custom exceptions
        } catch (Exception ex) {
            throw new DatabaseException("Error occurred while saving products: " + ex.getMessage());
        }
    }

}