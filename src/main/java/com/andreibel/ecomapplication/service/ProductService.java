package com.andreibel.ecomapplication.service;

import com.andreibel.ecomapplication.DTO.ProductRequest;
import com.andreibel.ecomapplication.DTO.ProductResponse;
import com.andreibel.ecomapplication.model.Product;
import com.andreibel.ecomapplication.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = new Product();
        updateProductFromRequest(product, productRequest);
        Product savedProduct = productRepository.save(product);
        return MapToProductResponse(savedProduct);
    }

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

    private void updateProductFromRequest(Product p, ProductRequest pr) {
        p.setName(pr.getName());
        p.setDescription(pr.getDescription());
        p.setPrice(pr.getPrice());
        p.setStockQuantity(pr.getStockQuantity());
        p.setCategory(pr.getCategory());
        p.setImageUrl(pr.getImageUrl());

    }


    public List<ProductResponse> getProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::MapToProductResponse)
                .toList();
    }

    public Optional<ProductResponse> updateProduct(Long id, ProductRequest productRequest) {
        return productRepository.findById(id)
                .map(product -> {
                    updateProductFromRequest(product, productRequest);
                    Product saved = productRepository.save(product);
                    return MapToProductResponse(saved);
                });
    }
}
