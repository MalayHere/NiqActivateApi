package com.niqactivate.niqactivateapi.controller;
import com.niqactivate.niqactivateapi.entity.Shopper;
import com.niqactivate.niqactivateapi.service.impl.ShopperServiceImpl;
import com.niqactivate.niqactivateapi.utils.PagedResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.EqualIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Conjunction;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

@RestController
public class EcommerceController {



    private final ShopperServiceImpl shopperService;

    @Autowired
    EcommerceController(ShopperServiceImpl shopperService)
    {
        this.shopperService=shopperService;
    }

    @Operation(
            summary = "get the products based on shopper id",
            description = "This endpoint exposes the functionality for fetching " +
                    "the products based on shopper id to the end user. " +
                    "In the parameter list shopperId is mandatory field, category," +
                    "brand and limit are non mandatory fields and limit takes a default value" +
                    "of 10." +
                    "The function returns a list of product details from the database")
    @GetMapping("/getProducts")
    public PagedResponse<Shopper> getProducts(@RequestParam(name="shopperId") String shopperId,
                                              @RequestParam(value = "category", required = false) String category,
                                              @RequestParam(value = "brand", required = false) String brand,
                                              @RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                              @Valid @RequestParam(value = "size", defaultValue = "10", required = false)@Max(100) int size,
                                              @RequestParam(value = "sort", defaultValue = "id", required = false) String sort,
                                              @RequestParam(value = "order", defaultValue = "desc", required = false) String order,
                                              @Conjunction({@Or(@Spec(path = "shopperId", params = "shopperId", spec = EqualIgnoreCase.class)),
                                                      @Or(@Spec(path = "productDetails.category", params = "category", spec = Equal.class)),
                                                      @Or(@Spec(path = "productDetails.brand", params = "brand", spec = Equal.class)) }) Specification<Shopper> spec
                                                ) {

        Pageable pageable = (size != 0
                ? PageRequest.of(page - 1, size, order.trim().equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC,
                sort)
                : Pageable.unpaged());

        return shopperService.findAll(pageable, spec);
    }
    }