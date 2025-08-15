package com.andreibel.ecomapplication.repository;

import com.andreibel.ecomapplication.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByActiveTrue();

}