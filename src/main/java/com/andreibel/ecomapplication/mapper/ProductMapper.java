package com.andreibel.ecomapplication.mapper;

import com.andreibel.ecomapplication.DTO.ProductRequestDTO;
import com.andreibel.ecomapplication.DTO.ProductResponseDTO;
import com.andreibel.ecomapplication.model.Product;

public class ProductMapper {

    /**
     * Maps a Product entity to a ProductResponse DTO.
     *
     * @param sp the Product entity to map
     * @return ProductResponse containing product details
     */
    public static ProductResponseDTO MapToProductResponse(Product sp) {
        return new ProductResponseDTO(sp.getId(), sp.getName(), sp.getDescription(), sp.getPrice(), sp.getStockQuantity(), sp.getCategory(), sp.getImageUrl(), sp.getActive());
    }


    /**
     * Updates the fields of a Product entity with values from a ProductRequest.
     *
     * @param p  the Product entity to update
     * @param pr the ProductRequest containing new values
     */
    public static void updateProductFromRequest(Product p, ProductRequestDTO pr) {
        p.setName(pr.name());
        p.setDescription(pr.description());
        p.setPrice(pr.price());
        p.setStockQuantity(pr.stockQuantity());
        p.setCategory(pr.category());
        p.setImageUrl(pr.imageUrl());
    }
}
