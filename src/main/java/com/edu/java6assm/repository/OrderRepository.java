package com.edu.java6assm.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.java6assm.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query("SELECT o FROM Order o WHERE o.user.username=?1")
    List<Order> findByUsername(String username);

    @Query("SELECT o FROM Order o WHERE o.user.id=?1")
    List<Order> findById(Integer id);
}
