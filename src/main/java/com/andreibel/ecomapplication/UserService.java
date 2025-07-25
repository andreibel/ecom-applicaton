package com.andreibel.ecomapplication;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private List<User> users = new ArrayList<>();

    public List<User> fetchAllUsers() {
        return users;
    }

    public void addUser(User user) {
        user.setId((long) (users.size() + 1)); // Simple ID generation
        users.add(user);

    }

    public Optional<User> getUser(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }


    public boolean updateUser(Long id, User updatedUser) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .map(user -> {
                    user.setFirstName(updatedUser.getFirstName());
                    user.setLastName(updatedUser.getLastName());
                    return true;
                })
                .orElse(false);

    }
}
