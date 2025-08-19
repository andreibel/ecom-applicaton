package com.andreibel.ecomapplication.service;

import com.andreibel.ecomapplication.DTO.OrderResponseDTO;
import com.andreibel.ecomapplication.exceptions.CartIsEmptyException;
import com.andreibel.ecomapplication.exceptions.UserNotFoundException;
import com.andreibel.ecomapplication.mapper.OrderMapper;
import com.andreibel.ecomapplication.model.*;
import com.andreibel.ecomapplication.repository.OrderRepository;
import com.andreibel.ecomapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartService cardService;
    private final OrderRepository OrderRepository;
    private final UserRepository userRepository;
    private final CartService cartService;
    private final OrderMapper OrderMapper;

    public OrderResponseDTO createOrder(Long userId) {
        // validate if the user exists first before searching for cart items
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        List<CartItem> cartItems = cardService.getCartById(user.getId()); // use query method to get cart items by
        // user ID without needing to pass the user object and avoid N+1 problem
        if (cartItems.isEmpty()) throw new CartIsEmptyException("Cart is empty, cannot create order");
        BigDecimal totalPrice = cartItems.stream().map(CartItem::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = new Order();
        order.setUser(user);
        order.setTotalAmout(totalPrice);
        order.setOrderStatus(OrderStatus.CONFIRMED);
        order.setOrderItems(cartItems.stream()
                .map(item -> new OrderItem(
                        null,
                        item.getProduct(),
                        order,
                        item.getPrice(),
                        item.getQuantity()
                )).toList());
        Order savedOrder = OrderRepository.save(order);
        cartService.clearCart(user.getId());
        return OrderMapper.toOrderResponseDTO(savedOrder);
    }

}
