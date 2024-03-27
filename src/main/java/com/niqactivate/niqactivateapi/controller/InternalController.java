package com.niqactivate.niqactivateapi.controller;

import com.niqactivate.niqactivateapi.dto.PersonalizedProductList;
import com.niqactivate.niqactivateapi.dto.ProductMetadata;
import com.niqactivate.niqactivateapi.exception.DatabaseException;
import com.niqactivate.niqactivateapi.exception.ErrorResponse;
import com.niqactivate.niqactivateapi.service.impl.PersonalizedProductListServiceImpl;
import com.niqactivate.niqactivateapi.service.impl.ProductDetailsServiceImpl;
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
public class InternalController {

    private final PersonalizedProductListServiceImpl personalizedProductService;
    private final ProductDetailsServiceImpl productDetailsService;
    Map<String,String> map = new HashMap<>();
    @Autowired
    public InternalController(PersonalizedProductListServiceImpl personalizedProductListService,
                              ProductDetailsServiceImpl productDetailsService) {
        this.personalizedProductService = personalizedProductListService;
        this.productDetailsService = productDetailsService;
    }

    @PostMapping("/personalized-products/add")
    public ResponseEntity<Object> addPersonalizedProduct(@RequestBody @Valid PersonalizedProductList personalizedProductList, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return buildErrorResponse(bindingResult, "Validation errors: ");
        }

        try {
            personalizedProductService.savePersonalizedProducts(personalizedProductList);
            map.clear();
            map.put("response", "Personalized Products added successfully." );
            return ResponseEntity.ok(map);
        } catch (DatabaseException ex) {
            return buildErrorResponse(ex);
        }
    }

    @PostMapping("/products/add")
    public ResponseEntity<Object> addProduct(@RequestBody @Valid @NotEmpty(message = "Product list cannot be empty") List<ProductMetadata> products, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return buildErrorResponse(bindingResult, "Validation errors for products:");
        }

        try {
            productDetailsService.saveProductsData(products);
            map.clear();
            map.put("response", "Products added successfully" );
            return ResponseEntity.ok(map);
        } catch (DatabaseException ex) {
            return buildErrorResponse(ex);
        }
    }

    private ResponseEntity<Object> buildErrorResponse(BindingResult bindingResult, String message) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : bindingResult.getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(new ErrorResponse("400", message + " " + String.join(", ", errors)));
    }

    private ResponseEntity<Object> buildErrorResponse(DatabaseException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("400", ex.getMessage()));
    }
}
