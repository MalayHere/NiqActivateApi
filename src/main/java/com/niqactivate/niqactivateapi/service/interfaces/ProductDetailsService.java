package com.niqactivate.niqactivateapi.service.interfaces;

import com.niqactivate.niqactivateapi.dto.ProductMetadata;

import java.util.List;

public interface ProductDetailsService {
    public void saveProductsData(List<ProductMetadata> products);
}
