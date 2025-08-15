package com.andreibel.ecomapplication.controller;

import com.andreibel.ecomapplication.DTO.ProductRequestDTO;
import com.andreibel.ecomapplication.DTO.ProductResponseDTO;
import com.andreibel.ecomapplication.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing products in the e-commerce application.
 * Provides endpoints for creating, updating, and retrieving products.
 */
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    /**
     * Service for product-related business logic.
     */
    private final ProductService productService;

    /**
     * Creates a new product.
     *
     * @param productRequest the product data to create
     * @return ResponseEntity containing the created product and HTTP status 201
     */
    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequest) {
        return new ResponseEntity<>(productService.createProduct(productRequest), HttpStatus.CREATED);
    }

    /**
     * Updates an existing product by ID.
     *
     * @param id             the ID of the product to update
     * @param productRequest the updated product data
     * @return ResponseEntity containing the updated product or 404 if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id,
                                                         @Valid @RequestBody ProductRequestDTO productRequest) {
        return productService.updateProduct(id, productRequest)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieves all products.
     *
     * @return ResponseEntity containing the list of all products
     */
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProduct() {
        return ResponseEntity.ok(productService.getProducts());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return productService.deleteProduct(id) ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}