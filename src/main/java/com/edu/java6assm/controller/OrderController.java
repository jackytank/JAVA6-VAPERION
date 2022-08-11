package com.edu.java6assm.controller;

import com.edu.java6assm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping("/order/checkout")
    public String checkout() {
        return "order/checkout";
    }

    @RequestMapping("/order/list")
    public String list(Model model, HttpServletRequest request){
        String username = request.getRemoteUser();
        model.addAttribute("orders",orderService.findByUsername(username));
        return "order/list";
    }

    @RequestMapping("/order/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("order", orderService.findById(id));
        return "order/detail";
    }
}
