package com.niqactivate.niqactivateapi.controller;

import com.niqactivate.niqactivateapi.dto.Product;
import com.niqactivate.niqactivateapi.entity.ProductDetails;
import com.niqactivate.niqactivateapi.service.impl.PersonalizedProductListServiceImpl;
import com.niqactivate.niqactivateapi.service.interfaces.PersonalizedProductListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ExternalController {

    @Autowired
    private PersonalizedProductListServiceImpl personalizedProductListService;

    @GetMapping("/{shopperId}")
    public ResponseEntity<Page<List<ProductDetails>>> getProductsByShopperId(
            @PathVariable String shopperId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false, defaultValue = "10") Integer limit){


            return  ResponseEntity.ok(personalizedProductListService.getProductsByShopper(shopperId,category,brand,limit));


        }
    }