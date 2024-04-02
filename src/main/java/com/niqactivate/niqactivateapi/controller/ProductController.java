package com.niqactivate.niqactivateapi.controller;

import com.niqactivate.niqactivateapi.dto.ShopperDTO;
import com.niqactivate.niqactivateapi.dto.ProductDTO;
import com.niqactivate.niqactivateapi.exception.DatabaseException;
import com.niqactivate.niqactivateapi.exception.ErrorResponse;
import com.niqactivate.niqactivateapi.service.impl.ShopperServiceImpl;
import com.niqactivate.niqactivateapi.service.impl.ProductDetailsServiceImpl;
import com.niqactivate.niqactivateapi.utils.ErrorResponseBuilder;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    private final ErrorResponseBuilder errorResponseBuilder;
    private final ProductDetailsServiceImpl productDetailsService;
    Map<String,String> map = new HashMap<>();
    @Autowired
    public ProductController(ErrorResponseBuilder errorResponseBuilder,
                             ProductDetailsServiceImpl productDetailsService) {
        this.errorResponseBuilder = errorResponseBuilder;
        this.productDetailsService = productDetailsService;
    }



    @Operation(
            summary = "add product",
            description = "This endpoint is used to add the product metadata " +
                    "coming from the data team and store it in the database. " +
                    "The product meta data include productId, category & brand."
    )
    @PostMapping("/products/add")
    public ResponseEntity<Object> addProduct(@RequestBody @Valid @NotEmpty(message = "Product list cannot be empty") List<ProductDTO> products, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return errorResponseBuilder.buildErrorResponse(bindingResult, "Validation errors for products:");
        }

        try {
            productDetailsService.saveProductsData(products);
            map.clear();
            map.put("response", "Products added successfully" );
            return ResponseEntity.ok(map);
        } catch (DatabaseException ex) {
            return errorResponseBuilder.buildErrorResponse(ex);
        }
    }


}
