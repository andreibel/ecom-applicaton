package com.andreibel.ecomapplication.controller;

import com.andreibel.ecomapplication.DTO.UserRequestDTO;
import com.andreibel.ecomapplication.DTO.UserResponseDTO;
import com.andreibel.ecomapplication.exceptions.UserNotFoundException;
import com.andreibel.ecomapplication.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing users in the e-commerce application.
 * Provides endpoints for creating, updating, and retrieving users.
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    /** Service for user-related business logic. */
    private final UserService userService;

    /**
     * Retrieves all users.
     *
     * @return ResponseEntity containing a list of all users
     */
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.fetchAllUsers());
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id the ID of the user to retrieve
     * @return ResponseEntity containing the user if found, or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id) {
        return userService.getUser(id).map(ResponseEntity::ok)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
    }

    /**
     * Updates an existing user by ID.
     *
     * @param id the ID of the user to update
     * @param userRequest the new user data
     * @return ResponseEntity with a success message or 404 if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDTO userRequest) {
        return userService.updateUser(id, userRequest) ?
                ResponseEntity.ok("User updated successfully") :
                ResponseEntity.notFound().build();
    }

    /**
     * Adds a new user.
     *
     * @param userRequest the user data to add
     * @return ResponseEntity with a success message
     */
    @PostMapping
    public ResponseEntity<String> addUser(@Valid @RequestBody UserRequestDTO userRequest) {
        userService.addUser(userRequest);
        return ResponseEntity.ok("User added successfully");
    }
}