package com.edu.java6assm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.java6assm.entity.OrderDetails;

public interface OrderDetailRepository extends JpaRepository<OrderDetails, Long> {
}
