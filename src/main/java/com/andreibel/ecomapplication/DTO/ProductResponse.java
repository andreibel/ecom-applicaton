package com.andreibel.ecomapplication.DTO;

import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
    private String category;
    private String imageUrl;
    private Boolean active;
}
