package com.niqactivate.niqactivateapi.service.interfaces;

import com.niqactivate.niqactivateapi.dto.Product;
import com.niqactivate.niqactivateapi.entity.ProductDetails;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PersonalizedProductListService {

    public Page<List<ProductDetails>> getProductsByShopper(String shopperId, String category, String brand, int limit);


}
