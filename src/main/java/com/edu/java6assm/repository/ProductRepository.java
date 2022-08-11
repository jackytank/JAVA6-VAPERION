package com.edu.java6assm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.java6assm.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.category.id=?1")
    List<Product> findByCategoryId(Integer cid);

    @Query("SELECT p FROM Product p WHERE " +
            "p.name LIKE CONCAT('%',:query, '%')" +
            "or p.category.name LIKE CONCAT('%', :query, '%')")
    List<Product> searchProducts(String query);

    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :start AND :end")
    List<Product> findBetweenPrices(Double start, Double end);

}
