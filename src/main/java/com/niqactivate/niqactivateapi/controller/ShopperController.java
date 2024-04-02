package com.niqactivate.niqactivateapi.controller;
import com.niqactivate.niqactivateapi.dto.ShopperDTO;
import com.niqactivate.niqactivateapi.exception.DatabaseException;
import com.niqactivate.niqactivateapi.service.impl.ShopperServiceImpl;
import com.niqactivate.niqactivateapi.utils.ErrorResponseBuilder;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ShopperController {
    private final ErrorResponseBuilder errorResponseBuilder;
    private final ShopperServiceImpl shopperService;
    Map<String,String> map = new HashMap<>();
    @Autowired
    public ShopperController(ErrorResponseBuilder errorResponseBuilder,
                             ShopperServiceImpl shopperService) {
        this.errorResponseBuilder = errorResponseBuilder;
        this.shopperService = shopperService;
    }
    @Operation(
            summary = "add shopper's personalised information",
            description = "when a user from the data team wants to add shopper's " +
                    "personalised information they can use the /personalized-products/add " +
                    "endpoint. This endpoint takes shopperId, productId & relevancyScore " +
                    "as the input and stores it in the database."
    )
    @PostMapping("/personalized-products/add")
    public ResponseEntity<Object> addPersonalizedProduct(@RequestBody @Valid ShopperDTO shopperDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return errorResponseBuilder.buildErrorResponse(bindingResult, "Validation errors: ");
        }

        try {
            shopperService.savePersonalizedProducts(shopperDTO);
            map.clear();
            map.put("response", "Personalized Products added successfully." );
            return ResponseEntity.ok(map);
        } catch (DatabaseException ex) {
            return errorResponseBuilder.buildErrorResponse(ex);
        }
    }
}
