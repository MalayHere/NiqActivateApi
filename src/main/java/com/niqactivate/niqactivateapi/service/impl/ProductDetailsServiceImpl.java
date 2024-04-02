package com.niqactivate.niqactivateapi.service.impl;

import com.niqactivate.niqactivateapi.dto.ProductDTO;
import com.niqactivate.niqactivateapi.entity.Product;
import com.niqactivate.niqactivateapi.exception.DatabaseException;
import com.niqactivate.niqactivateapi.repository.interfaces.ProductDetailsRepository;
import com.niqactivate.niqactivateapi.service.interfaces.ProductDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductDetailsServiceImpl implements ProductDetailsService {
    private final ProductDetailsRepository productDetailsRepository;
    private final ModelMapper modelMapper;

    ProductDetailsServiceImpl(ProductDetailsRepository productDetailsRepository, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.productDetailsRepository = productDetailsRepository;
    }

    @Override
    public void saveProductsData(List<ProductDTO> products) {
        for(ProductDTO productDTO: products){
            if(productDetailsRepository.existsById(productDTO.getProductId())){
                throw new DatabaseException("Product already exists!");
            }
        }
        try {
            productDetailsRepository.saveAll(products.stream()
                    .map(product -> modelMapper.map(product, Product.class))
                    .collect(Collectors.toList()));
        } catch (Exception ex) {
            throw new DatabaseException("Error occurred while saving products: " + ex.getMessage());
        }
    }
}
