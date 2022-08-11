package com.edu.java6assm.service;

import java.util.List;

import com.edu.java6assm.entity.Product;



public interface ProductService {
    List<Product> findAll();

    Product findById(Integer id);

    List<Product> findByCategoryId(Integer cid);

    List<Product> searchProducts(String query);

    Product create(Product product);

    Product update(Product product);

    void delete(Integer id);
}
