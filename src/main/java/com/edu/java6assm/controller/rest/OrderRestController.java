package com.edu.java6assm.controller.rest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edu.java6assm.entity.Order;
import com.edu.java6assm.entity.OrderDetails;
import com.edu.java6assm.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;

@CrossOrigin("*")
@RestController
public class OrderRestController {
    @Autowired
    OrderService orderService;

    @GetMapping("/rest/orders")
    public ResponseEntity<List<Order>> getOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @PostMapping("/rest/orders")
    public ResponseEntity<Order> create(@RequestBody JsonNode orderData) {
        if (orderData.get("total").asDouble() == 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(orderService.create(orderData));
    }

    @GetMapping("/rest/orders/list/{username}")
    public ResponseEntity<List<Order>> list(ModelMap model, @PathVariable("username") Optional<String> username,
            HttpServletRequest request) {
        try {
            if (username.isPresent()) {
                List<Order> listOrders = orderService.findByUsername(username.get());
                return ResponseEntity.ok().body(listOrders);
            }
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/rest/orders/detail/{orderId}")
    public ResponseEntity<List<OrderDetails>> getOrderDetail(ModelMap model,
            @PathVariable("orderId") Optional<Long> orderId,
            HttpServletRequest request) {
        try {
            if (orderId.isPresent()) {
                List<OrderDetails> orderDetails = orderService.findById(orderId.get()).getOrder_details();
                return ResponseEntity.ok().body(orderDetails);
            }
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/rest/orders/{orderId}")
    public ResponseEntity<Order> getOrder(ModelMap model, @PathVariable("orderId") Optional<Long> orderId,
            HttpServletRequest request) {
        try {
            if (orderId.isPresent()) {
                Order order = orderService.findById(orderId.get());
                return ResponseEntity.ok().body(order);
            }
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
