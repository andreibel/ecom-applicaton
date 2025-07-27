package com.andreibel.ecomapplication.controller;

import com.andreibel.ecomapplication.DTO.ProductRequest;
import com.andreibel.ecomapplication.DTO.ProductResponse;
import com.andreibel.ecomapplication.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        return new ResponseEntity<>(productService.createProduct(productRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id,
                                                         @RequestBody ProductRequest productRequest) {
        return productService.updateProduct(id, productRequest)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProduct() {
        return ResponseEntity.ok(productService.getProducts());
    }
}
