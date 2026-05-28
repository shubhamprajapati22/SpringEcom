package org.example.springecom.repository;

import org.example.springecom.model.Cart;
import org.example.springecom.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CartItemRepo extends JpaRepository<CartItem, Integer> {
    @Modifying
    @Query("delete from CartItem c where c.cart.id = :id")
    void deleteByCartId(int id);
    @Modifying
    @Query("delete from CartItem c where c.cart.id = :cartId and c.product.id = :productId")
    void deleteByCartIdAndProductId(Integer cartId, int productId);
}
