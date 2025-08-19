package com.andreibel.ecomapplication.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        log.warn("Email already exists: {}", ex.getMessage());
        Map<String, String> error = new HashMap<>();
        error.put("message", "Email address already exists");
        return ResponseEntity.status(409).body(error); // 409 Conflict
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundException ex) {
        log.warn("User not found: {}", ex.getMessage());
        Map<String, String> error = new HashMap<>();
        error.put("message", "User not found");
        return ResponseEntity.status(404).body(error); // 404 Not Found
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleProductNotFoundException(ProductNotFoundException ex) {
        log.warn("Product not found: {}", ex.getMessage());
        Map<String, String> error = new HashMap<>();
        error.put("message", "Product not found");
        return ResponseEntity.status(404).body(error); // 404 Not Found
    }

    @ExceptionHandler(NotEnoughProductException.class)
    public ResponseEntity<Map<String, String>> handleNotEnoughProductException(NotEnoughProductException ex) {
        log.warn("Not enough product in stock: {}", ex.getMessage());
        Map<String, String> error = new HashMap<>();
        error.put("message", "Not enough product in stock");
        return ResponseEntity.status(400).body(error); // 400 Bad Request
    }
    @ExceptionHandler(CartIsEmptyException.class)
    public ResponseEntity<Map<String, String>> handleCartIsEmptyException(CartIsEmptyException ex) {
        log.warn("Cart is empty: {}", ex.getMessage());
        Map<String, String> error = new HashMap<>();
        error.put("message", "Cart is empty, cannot create order");
        return ResponseEntity.status(400).body(error); // 404 Not Found
    }
}
