package com.niqactivate.niqactivateapi.utils;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
public class ShelfItem{
    @NotBlank(message = "Product ID is required")
    private String productId;
    @NotBlank(message = "Relevancy is required")
    private BigDecimal relevancyScore;
}