package com.edu.java6assm.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.java6assm.entity.Product;
import com.edu.java6assm.service.ProductService;

@Controller
public class ProductController {

    @Autowired
    ProductService service;

    @RequestMapping("/product/list")
    public String list(ModelMap model, @RequestParam("cid") Optional<Integer> cid) {
        // if (cid.isPresent()) {
        //     List<Product> items = service.findByCategoryId(cid.get());
        //     model.addAttribute("items", items);
        // } else {
        //     List<Product> items = service.findAll();
        //     model.addAttribute("items", items);
        // }
        return "product/list";
    }

    @RequestMapping("/product/detail/{id}")
    public String detail(ModelMap model, @PathVariable("id") Integer id) {
        Product item = service.findById(id);
        model.addAttribute("item", item);
        return "product/detail";
    }
}