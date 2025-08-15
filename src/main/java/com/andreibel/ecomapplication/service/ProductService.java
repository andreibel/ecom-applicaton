package com.andreibel.ecomapplication.service;

import com.andreibel.ecomapplication.DTO.ProductRequestDTO;
import com.andreibel.ecomapplication.DTO.ProductResponseDTO;
import com.andreibel.ecomapplication.mapper.ProductMapper;
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
    public ProductResponseDTO createProduct(ProductRequestDTO productRequest) {
        Product product = new Product();
        ProductMapper.updateProductFromRequest(product, productRequest);
        return ProductMapper.MapToProductResponse(saveProduct(product));
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
     * Retrieves all products from the database.
     *
     * @return List of ProductResponse DTOs for all products
     */
    public List<ProductResponseDTO> getProducts() {
        List<Product> products = productRepository.findByActiveTrue();
        return products.stream()
                .map(ProductMapper::MapToProductResponse)
                .toList();
    }

    /**
     * Updates an existing product by ID with new data.
     *
     * @param id             the ID of the product to update
     * @param productRequest the new product data
     * @return Optional containing the updated ProductResponse, or empty if not found
     */
    public Optional<ProductResponseDTO> updateProduct(Long id, ProductRequestDTO productRequest) {
        return productRepository.findById(id)
                .map(product -> {
                    ProductMapper.updateProductFromRequest(product, productRequest);
                    return ProductMapper.MapToProductResponse(saveProduct(product));
                });
    }

    public boolean deleteProduct(Long id) {
        return productRepository.findById(id).map(
                product -> {
                    product.setActive(false);
                    saveProduct(product);
                    return true;
                }
        ).orElse(false);
    }
}