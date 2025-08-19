package com.andreibel.ecomapplication.controller;


import com.andreibel.ecomapplication.DTO.CartItemRequestDTO;
import com.andreibel.ecomapplication.model.CartItem;
import com.andreibel.ecomapplication.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<Void> addProductToCart(@RequestHeader("X-User-ID") Long userId,
                                                 @RequestBody CartItemRequestDTO request) {
        cartService.addToCart(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @DeleteMapping("items/{productId}")
    public ResponseEntity<Void> removeFromCart(@RequestHeader("X-User-ID") Long userId,
                                               @RequestParam Long productId) {
        cartService.deleteItemFromCart(userId, productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getCartItems(@RequestHeader("X-User-ID") Long userId) {
        return ResponseEntity.ok(cartService.getCart(userId));
    }

}
