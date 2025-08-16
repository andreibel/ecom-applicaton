package com.andreibel.ecomapplication.repository;

import com.andreibel.ecomapplication.model.Oder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OderRepository extends JpaRepository<Oder, Long> {
}