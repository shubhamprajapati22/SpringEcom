package org.example.springecom.repository;

import org.example.springecom.model.Orders;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Orders, Integer> {
    @Override
    @EntityGraph(attributePaths = {"user", "ordersItems", "ordersItems.product", "payment"})
    List<Orders> findAll();

    List<Orders> findAllByUserEmail(String email);
}
