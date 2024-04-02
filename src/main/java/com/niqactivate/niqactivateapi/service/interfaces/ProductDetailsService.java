package com.niqactivate.niqactivateapi.service.interfaces;

import com.niqactivate.niqactivateapi.dto.ProductDTO;

import java.util.List;

public interface ProductDetailsService {
    public void saveProductsData(List<ProductDTO> products);
}
