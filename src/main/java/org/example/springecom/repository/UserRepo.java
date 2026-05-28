package org.example.springecom.repository;

import org.example.springecom.model.Cart;
import org.example.springecom.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);
    //@Query("select u.cart from Users u where ")
    Cart findCartByEmail(String email);
}
