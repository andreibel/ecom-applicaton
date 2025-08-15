package com.andreibel.ecomapplication.mapper;

import com.andreibel.ecomapplication.DTO.AddressDTO;
import com.andreibel.ecomapplication.DTO.UserRequestDTO;
import com.andreibel.ecomapplication.DTO.UserResponseDTO;
import com.andreibel.ecomapplication.model.Address;
import com.andreibel.ecomapplication.model.User;

/**
 * Utility class for mapping between User entities and their corresponding DTOs.
 */
public class UserMapper {

    /**
     * Maps the fields from a UserRequestDTO to a User entity.
     * Updates the provided User object with values from the DTO, including nested address information if present.
     *
     * @param user           the User entity to update
     * @param userRequestDTO the DTO containing user data
     */
    public static void mapToUser(User user, UserRequestDTO userRequestDTO) {
        user.setFirstName(userRequestDTO.firstName());
        user.setLastName(userRequestDTO.lastName());
        user.setEmail(userRequestDTO.email());
        user.setPhone(userRequestDTO.phone());

        if (userRequestDTO.address() != null) {
            Address address = new Address();
            address.setCity(userRequestDTO.address().city());
            address.setState(userRequestDTO.address().state());
            address.setCountry(userRequestDTO.address().country());
            address.setZipCode(userRequestDTO.address().zipCode());
            address.setStreet(userRequestDTO.address().street());

            user.setAddress(address);
        }
    }

    /**
     * Maps a User entity to a UserResponseDTO.
     * Converts the User and its associated Address to their DTO representations.
     *
     * @param u the User entity to map
     * @return a UserResponseDTO containing user and address details
     */
    public static UserResponseDTO mapToUserResponse(User u) {
        return new UserResponseDTO(
                u.getId(),
                u.getFirstName(),
                u.getLastName(),
                u.getEmail(),
                u.getPhone(),
                u.getUserRule(),
                new AddressDTO(
                        u.getAddress().getStreet(),
                        u.getAddress().getCity(),
                        u.getAddress().getState(),
                        u.getAddress().getCountry(),
                        u.getAddress().getZipCode()
                )
        );
    }
}