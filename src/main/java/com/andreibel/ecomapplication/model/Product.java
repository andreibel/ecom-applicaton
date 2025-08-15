package com.andreibel.ecomapplication.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity(name = "products")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private Double price;
    @NotNull
    private Integer stockQuantity;
    @NotNull
    private String category;
    @NotNull
    private String imageUrl;
    @NotNull
    private Boolean active = true;
    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;

}
