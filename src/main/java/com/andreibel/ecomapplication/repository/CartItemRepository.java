package com.andreibel.ecomapplication.repository;

import com.andreibel.ecomapplication.model.CartItem;
import com.andreibel.ecomapplication.model.Product;
import com.andreibel.ecomapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByUserAndProduct(User user, Product product);

    long deleteByUserAndProduct(User user, Product product);

    List<CartItem> findByUser(User user);
    List<CartItem> findByUser_Id(Long userId);


    void deleteByUser_Id(Long userId);
}