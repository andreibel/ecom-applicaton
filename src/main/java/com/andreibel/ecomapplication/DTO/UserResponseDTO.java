package com.andreibel.ecomapplication.DTO;

import com.andreibel.ecomapplication.model.UserRule;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.andreibel.ecomapplication.model.User}
 */
public record UserResponseDTO(@NotNull Long id, @NotNull String firstName, @NotNull String lastName,
                              @NotNull @Email String email,
                              @NotNull String phone, UserRule userRule, AddressDTO address) implements Serializable {
}