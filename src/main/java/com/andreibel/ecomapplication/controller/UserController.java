package com.andreibel.ecomapplication.controller;

import com.andreibel.ecomapplication.DTO.UserRequest;
import com.andreibel.ecomapplication.DTO.UserResponse;
import com.andreibel.ecomapplication.model.User;
import com.andreibel.ecomapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.fetchAllUsers());
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        return userService.getUser(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id,  @RequestBody UserRequest userRequest) {
        return userService.updateUser(id, userRequest) ?
                ResponseEntity.ok("User updated successfully") :
                ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody UserRequest userRequest) {
        userService.addUser(userRequest);
        return ResponseEntity.ok("User added successfully");
    }
}
