package com.andreibel.ecomapplication.controller;

import com.andreibel.ecomapplication.DTO.OrderResponseDTO;
import com.andreibel.ecomapplication.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestHeader("X-User-ID") Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(userId));
    }

}
