package com.edu.java6assm.controller.rest;

import com.edu.java6assm.entity.Order;
import com.edu.java6assm.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
public class OrderRestController {
    @Autowired
    OrderService orderService;

    @PostMapping("/rest/orders")
    public Order create(@RequestBody JsonNode orderData){
        return orderService.create(orderData);
    }
}
