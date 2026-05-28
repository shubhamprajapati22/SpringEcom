package org.example.springecom.repository;

import org.example.springecom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {// Integer is type of primary key

    @Query("select p from Product p where lower(p.name) like %:keyword% or" +
            " lower(p.description) like %:keyword% or " +
            "lower(p.brand) like %:keyword% or " +
            "lower(p.category) like %:keyword%")
    List<Product> searchByKeyword(String keyword);

}
