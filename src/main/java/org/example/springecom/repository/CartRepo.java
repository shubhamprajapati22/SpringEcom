package org.example.springecom.repository;

import org.example.springecom.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepo extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByUserId(int userId);

    Optional<Cart> findByUserEmail(String email);
}


