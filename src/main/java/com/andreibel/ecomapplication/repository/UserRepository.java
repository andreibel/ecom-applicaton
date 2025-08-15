package com.andreibel.ecomapplication.repository;

import com.andreibel.ecomapplication.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmailAndIdNot(@NotNull @Email(message = "need current Email") String email, Long id);

    boolean existsByEmail(@NotNull @Email(message = "need current Email") String email);
}

