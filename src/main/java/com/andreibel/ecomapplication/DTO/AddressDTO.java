package com.andreibel.ecomapplication.DTO;

import com.andreibel.ecomapplication.model.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link Address}
 */
public record AddressDTO(@NotNull @NotBlank(message = "need name of street") String street,
                         @NotNull @NotBlank(message = "need name of city") String city,
                         @NotNull @NotBlank(message = "need name of state") String state,
                         @NotNull @NotBlank(message = "need name of  contry") String country,
                         @NotNull @NotBlank(message = "need zip code ") String zipCode) implements Serializable {
}