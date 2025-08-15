package com.andreibel.ecomapplication.service;

import com.andreibel.ecomapplication.DTO.UserRequestDTO;
import com.andreibel.ecomapplication.DTO.UserResponseDTO;
import com.andreibel.ecomapplication.exceptions.EmailAlreadyExistsException;
import com.andreibel.ecomapplication.mapper.UserMapper;
import com.andreibel.ecomapplication.model.User;
import com.andreibel.ecomapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for handling user-related business logic.
 * Provides methods for creating, retrieving, and updating users.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    /**
     * Repository for accessing user data from the database.
     */
    private final UserRepository userRepository;

    /**
     * Retrieves all users from the database and maps them to UserResponseDTOs.
     *
     * @return List of UserResponseDTO objects representing all users
     */
    public List<UserResponseDTO> fetchAllUsers() {
        return userRepository.findAll().stream().map(UserMapper::mapToUserResponse).collect(Collectors.toList());
    }

    /**
     * Adds a new user to the database using the provided UserRequestDTO.
     *
     * @param user the UserRequestDTO containing user data to add
     */
    @Transactional
    public void addUser(UserRequestDTO user) {
        User newUser = new User();
        if (userRepository.existsByEmail(user.email())) {
            throw new EmailAlreadyExistsException("Email already exists: " + user.email());
        }
        UserMapper.mapToUser(newUser, user);
        userRepository.save(newUser);
    }

    /**
     * Retrieves a user by ID and maps it to a UserResponseDTO.
     *
     * @param id the ID of the user to retrieve
     * @return Optional containing the UserResponseDTO if found, or empty if not found
     */
    public Optional<UserResponseDTO> getUser(Long id) {
        return userRepository.findById(id).map(UserMapper::mapToUserResponse);
    }

    /**
     * Updates an existing user by ID with new data from UserRequestDTO.
     * The operation is transactional to ensure data consistency.
     *
     * @param id             the ID of the user to update
     * @param userRequestDTO the new user data
     * @return true if the user was found and updated, false otherwise
     */
    @Transactional
    public boolean updateUser(Long id, UserRequestDTO userRequestDTO) {
        // Check if the email is already used by another user
        if (userRepository.existsByEmailAndIdNot(userRequestDTO.email(), id)) {
            throw new EmailAlreadyExistsException("Email already exists: " + userRequestDTO.email());
        }
        return userRepository.findById(id).map(user -> {
            UserMapper.mapToUser(user, userRequestDTO);
            userRepository.save(user);
            return true;
        }).orElse(false);

    }
}