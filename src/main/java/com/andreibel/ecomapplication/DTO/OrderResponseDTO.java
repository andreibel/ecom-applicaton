package com.andreibel.ecomapplication.DTO;

import com.andreibel.ecomapplication.model.Order;
import com.andreibel.ecomapplication.model.OrderStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link Order}
 */
public record OrderResponseDTO(Long id, OrderStatus status, List<OrderItemDTO> items, BigDecimal totalAmout,
                               LocalDateTime createdAt) implements Serializable {
}