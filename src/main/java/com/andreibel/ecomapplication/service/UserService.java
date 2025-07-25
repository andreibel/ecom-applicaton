package com.andreibel.ecomapplication.service;

import com.andreibel.ecomapplication.DTO.AddressDTO;
import com.andreibel.ecomapplication.DTO.UserRequest;
import com.andreibel.ecomapplication.DTO.UserResponse;
import com.andreibel.ecomapplication.model.Address;
import com.andreibel.ecomapplication.model.User;
import com.andreibel.ecomapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponse> fetchAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

    public void addUser(UserRequest user) {
        User newUser = new User();
        mapToUser(newUser, user);
        userRepository.save(newUser);
    }

    public Optional<UserResponse> getUser(Long id) {
        return userRepository.findById(id).map(this::mapToUserResponse);
    }

    @Transactional
    public boolean updateUser(Long id, UserRequest updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    mapToUser(user, updatedUser);
                    userRepository.save(user);
                    return true;
                })
                .orElse(false);

    }

    private UserResponse mapToUserResponse(User u) {
        UserResponse us = new UserResponse();
        us.setId(u.getId().toString());
        us.setFirstName(u.getFirstName());
        us.setLastName(u.getLastName());
        us.setEmail(u.getEmail());
        us.setPhone(u.getPhone());
        us.setUserRule(u.getUserRule());
        if (u.getAddress() != null) {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setId(u.getAddress().getId().toString());
            addressDTO.setStreet(u.getAddress().getStreet());
            addressDTO.setCity(u.getAddress().getCity());
            addressDTO.setState(u.getAddress().getState());
            addressDTO.setCountry(u.getAddress().getCountry());
            addressDTO.setZipCode(u.getAddress().getZipCode());
            us.setAddress(addressDTO);
        }
        return us;
    }
    private void mapToUser(User user, UserRequest userRequest) {
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());

        if (userRequest.getAddress() != null) {
            Address address = new Address();
            address.setCity(userRequest.getAddress().getCity());
            address.setState(userRequest.getAddress().getState());
            address.setCountry(userRequest.getAddress().getCountry());
            address.setZipCode(userRequest.getAddress().getZipCode());
            address.setStreet(userRequest.getAddress().getStreet());

            user.setAddress(address);
        }
    }
}
