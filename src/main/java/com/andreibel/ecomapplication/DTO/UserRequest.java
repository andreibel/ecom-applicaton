package com.andreibel.ecomapplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private AddressDTO address;
}
