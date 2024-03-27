package com.niqactivate.niqactivateapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductMetadata {
        @NotBlank(message = "Product ID is required")
        private String productId;

        @NotBlank(message = "Category is required")
        private String category;

        @NotBlank(message = "Brand is required")
        private String brand;
}
