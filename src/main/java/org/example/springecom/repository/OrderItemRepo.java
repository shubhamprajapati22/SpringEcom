package org.example.springecom.repository;

import org.example.springecom.model.OrdersItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepo extends JpaRepository<OrdersItem, Integer> {
}
