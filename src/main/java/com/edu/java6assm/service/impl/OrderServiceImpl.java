package com.edu.java6assm.service.impl;

import com.edu.java6assm.entity.Order;
import com.edu.java6assm.entity.OrderDetails;
import com.edu.java6assm.entity.User;
import com.edu.java6assm.repository.OrderDetailRepository;
import com.edu.java6assm.repository.OrderRepository;
import com.edu.java6assm.service.OrderService;
import com.edu.java6assm.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepo;

    @Autowired
    UserService userService;

    @Autowired
    OrderDetailRepository orderDetailRepo;

    @Override
    public Order create(JsonNode orderData) {
        // System.out.println("This is nodeeee:" + orderData.toPrettyString());

        ObjectMapper mapper = new ObjectMapper();
        Order order = mapper.convertValue(orderData, Order.class);
        User user = userService.findByUsername(order.getUser().getUsername()).get();
        order.setUser(user);
        orderRepo.save(order);
        List<OrderDetails> list = mapper
                .convertValue(orderData.get("order_details"), new TypeReference<List<OrderDetails>>() {
                })
                .stream().peek(o -> o.setOrder(order)).collect(Collectors.toList());
        orderDetailRepo.saveAll(list);
        return order;
    }

    @Override
    public Order findById(long id) {
        return orderRepo.findById(id).get();
    }

    @Override
    public List<Order> findByUsername(String username) {
        return orderRepo.findByUsername(username);
    }

    @Override
    public List<Order> findAll() {
        return orderRepo.findAll();
    }
}
