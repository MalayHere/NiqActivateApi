package com.niqactivate.niqactivateapi.controller;

import com.niqactivate.niqactivateapi.entity.ProductDetails;
import com.niqactivate.niqactivateapi.service.impl.PersonalizedProductListServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class EcommerceController {

    @Autowired
    private PersonalizedProductListServiceImpl personalizedProductListService;


    @Operation(
            summary = "get the products based on shopper id",
            description = "This endpoint exposes the functionality for fetching " +
                    "the products based on shopper id to the end user. " +
                    "In the parameter list shopperId is mandatory field, category," +
                    "brand and limit are non mandatory fields and limit takes a default value" +
                    "of 10." +
                    "The function returns a list of product details from the database")
    @GetMapping("/{shopperId}")
    public ResponseEntity<Page<List<ProductDetails>>> getProductsByShopperId(
            @PathVariable String shopperId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false, defaultValue = "10") Integer limit){


            return  ResponseEntity.ok(personalizedProductListService.getProductsByShopper(shopperId,category,brand,limit));


        }
    }