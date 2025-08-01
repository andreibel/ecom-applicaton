package com.andreibel.ecomapplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private String id;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}