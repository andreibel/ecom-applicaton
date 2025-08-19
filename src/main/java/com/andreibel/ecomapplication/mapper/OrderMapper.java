package com.andreibel.ecomapplication.mapper;

import com.andreibel.ecomapplication.DTO.OrderItemDTO;
import com.andreibel.ecomapplication.DTO.OrderResponseDTO;
import com.andreibel.ecomapplication.model.Order;
import com.andreibel.ecomapplication.model.OrderItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderMapper {

    public OrderResponseDTO toOrderResponseDTO(Order order) {
        return new OrderResponseDTO(
                order.getId(),
                order.getOrderStatus(),
                order.getOrderItems().stream().map(this::toOrder).toList(),
                order.getTotalAmout(),
                order.getCreatedAt()
        );
    }
    public OrderItemDTO toOrder(OrderItem orderItem) {
        return new OrderItemDTO(
                orderItem.getId(),
                orderItem.getProduct().getId(),
                orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity())),
                orderItem.getQuantity()
        );
    }
}

