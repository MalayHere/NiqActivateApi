package com.niqactivate.niqactivateapi.dto;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Product {


    private String productId;
    private String category;
    private String brand;


}
