package com.andreibel.ecomapplication.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

/**
 * DTO for {@link com.andreibel.ecomapplication.model.User}
 */
public record UserRequestDTO(@NotNull @NotBlank(message = "need first name") String firstName,
                             @NotNull @NotBlank(message = "need last name") String lastName,
                             @NotNull @Email(message = "need current Email") String email,
                             @NotNull @Pattern(message = "need correct phone number ", regexp = "^\\+?[1-9]\\d{1,14}$")
                             @NotBlank(message = "need phone number ") String phone,
                             @NotNull(message = "need address") AddressDTO address) implements Serializable {
}