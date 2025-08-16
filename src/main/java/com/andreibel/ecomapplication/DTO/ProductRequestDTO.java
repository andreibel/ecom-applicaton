package com.andreibel.ecomapplication.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.andreibel.ecomapplication.model.Product}
 */
public record ProductRequestDTO(@NotNull @NotBlank(message = "need product name") String name,
                                @NotNull @Size(message = "max description is 200 letters", max = 200) @NotBlank(message = "need description") String description,
                                @NotNull @PositiveOrZero(message = "price cant be negative") BigDecimal price,
                                @NotNull @PositiveOrZero(message = "stock cant be less then 0") Integer stockQuantity,
                                @NotNull @NotBlank(message = "need category name") String category,
                                @NotNull @URL(message = "need the url of the image") String imageUrl) implements Serializable {
}