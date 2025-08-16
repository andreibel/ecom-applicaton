package com.andreibel.ecomapplication.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.Serializable;

/**
 * DTO for {@link com.andreibel.ecomapplication.model.CartItem}
 */
public record CartItemRequestDTO(@NotNull Long productId,
                                 @NotNull @PositiveOrZero(message = "quantity can't me less then 0") Integer quantity) implements Serializable {
}