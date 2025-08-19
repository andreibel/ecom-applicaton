package com.andreibel.ecomapplication.service;

import com.andreibel.ecomapplication.DTO.CartItemRequestDTO;
import com.andreibel.ecomapplication.exceptions.NotEnoughProductException;
import com.andreibel.ecomapplication.exceptions.ProductNotFoundException;
import com.andreibel.ecomapplication.exceptions.UserNotFoundException;
import com.andreibel.ecomapplication.model.CartItem;
import com.andreibel.ecomapplication.model.Product;
import com.andreibel.ecomapplication.model.User;
import com.andreibel.ecomapplication.repository.CartItemRepository;
import com.andreibel.ecomapplication.repository.ProductRepository;
import com.andreibel.ecomapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    /**
     * Adds a product to the user's cart or updates the quantity if it already exists.
     * <p>
     * If the product is already in the user's cart, increases the quantity and updates the total price.
     * Otherwise, creates a new cart item for the user.
     * <p>
     * @param userId  the ID of the user adding the product to the cart
     * @param request the cart item request containing product ID and quantity
     * @throws com.andreibel.ecomapplication.exceptions.ProductNotFoundException  if the product is not found
     * @throws com.andreibel.ecomapplication.exceptions.NotEnoughProductException if there is not enough stock
     * @throws com.andreibel.ecomapplication.exceptions.UserNotFoundException     if the user is not found
     */
    @Transactional
    public void addToCart(Long userId, CartItemRequestDTO request) {
        Product product = productRepository.findById(request.productId()).map(
                        prod -> {
                            if (prod.getStockQuantity() < request.quantity())
                                throw new NotEnoughProductException("Not enough product in stock");
                            return prod;
                        })
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        cartItemRepository.findByUserAndProduct(user, product).map(
                cartItem -> {
                    cartItem.setQuantity(cartItem.getQuantity() + request.quantity());
                    cartItem.setPrice(cartItem.getPrice().add(product.getPrice().multiply(BigDecimal.valueOf(request.quantity()))));
                    return cartItemRepository.save(cartItem);
                }
        ).orElseGet(
                () -> {
                    CartItem newCartItem = new CartItem();
                    newCartItem.setUser(user);
                    newCartItem.setProduct(product);
                    newCartItem.setQuantity(request.quantity());
                    newCartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(request.quantity())));
                    return cartItemRepository.save(newCartItem);
                }
        );
    }

    @Transactional
    public void deleteItemFromCart(Long userId, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        if (cartItemRepository.deleteByUserAndProduct(user, product) == 0) {
            throw new ProductNotFoundException("Product not found in cart");
        }
    }

    public List<CartItem> getCart(Long userId) {
        return userRepository.findById(userId).map(cartItemRepository::findByUser)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }
    // Retrieves the cart items for a specific user by their ID. and not throw error if the user is not found
    // If the user is not found, it returns an empty list instead of throwing an exception
    public List<CartItem> getCartById(Long userId) {
        return cartItemRepository.findByUser_Id(userId);
    }


    public void clearCart(Long userId) {
        cartItemRepository.deleteByUser_Id(userId);
    }
}
