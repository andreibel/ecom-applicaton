package com.andreibel.ecomapplication.service;

import com.andreibel.ecomapplication.DTO.ProductRequest;
import com.andreibel.ecomapplication.DTO.ProductResponse;
import com.andreibel.ecomapplication.model.Product;
import com.andreibel.ecomapplication.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for handling product-related business logic.
 * Provides methods for creating, updating, and retrieving products.
 */
@Service
@RequiredArgsConstructor
public class ProductService {

    /**
     * Repository for accessing product data from the database.
     */
    private final ProductRepository productRepository;

    /**
     * Creates a new product from the given request data.
     *
     * @param productRequest the product data to create
     * @return ProductResponse containing the created product's details
     */
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = new Product();
        updateProductFromRequest(product, productRequest);
        return MapToProductResponse(saveProduct(product));
    }

    /**
     * Saves the given product to the database.
     *
     * @param product the product to save
     * @return the saved Product entity
     */
    private Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Maps a Product entity to a ProductResponse DTO.
     *
     * @param sp the Product entity to map
     * @return ProductResponse containing product details
     */
    private ProductResponse MapToProductResponse(Product sp) {
        return ProductResponse.builder()
                .id(sp.getId()).name(sp.getName())
                .description(sp.getDescription())
                .price(sp.getPrice())
                .stockQuantity(sp.getStockQuantity())
                .category(sp.getCategory())
                .imageUrl(sp.getImageUrl())
                .active(sp.getActive())
                .build();
    }

    /**
     * Updates the fields of a Product entity with values from a ProductRequest.
     *
     * @param p  the Product entity to update
     * @param pr the ProductRequest containing new values
     */
    private void updateProductFromRequest(Product p, ProductRequest pr) {
        p.setName(pr.getName());
        p.setDescription(pr.getDescription());
        p.setPrice(pr.getPrice());
        p.setStockQuantity(pr.getStockQuantity());
        p.setCategory(pr.getCategory());
        p.setImageUrl(pr.getImageUrl());
    }

    /**
     * Retrieves all products from the database.
     *
     * @return List of ProductResponse DTOs for all products
     */
    public List<ProductResponse> getProducts() {
        List<Product> products = productRepository.findByActiveTrue();
        return products.stream()
                .map(this::MapToProductResponse)
                .toList();
    }

    /**
     * Updates an existing product by ID with new data.
     *
     * @param id             the ID of the product to update
     * @param productRequest the new product data
     * @return Optional containing the updated ProductResponse, or empty if not found
     */
    public Optional<ProductResponse> updateProduct(Long id, ProductRequest productRequest) {
        return productRepository.findById(id)
                .map(product -> {
                    updateProductFromRequest(product, productRequest);
                    return MapToProductResponse(saveProduct(product));
                });
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setActive(false);
        productRepository.save(product);
    }
}