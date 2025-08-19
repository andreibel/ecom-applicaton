package com.andreibel.ecomapplication.DTO;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.andreibel.ecomapplication.model.OrderItem}
 */
public record OrderItemDTO(Long id, Long productId, BigDecimal price, Integer quantity) implements Serializable {
}