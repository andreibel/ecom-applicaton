package com.andreibel.ecomapplication.DTO;

import com.andreibel.ecomapplication.model.UserRule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserRule userRule;
    private AddressDTO address;
}
