package com.andreibel.ecomapplication.DTO;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.andreibel.ecomapplication.model.Product}
 */
public record ProductResponseDTO(Long id, @NotNull String name, @NotNull String description, @NotNull Double price,
                                 @NotNull Integer stockQuantity, @NotNull String category, @NotNull String imageUrl,
                                 @NotNull Boolean active) implements Serializable {
}