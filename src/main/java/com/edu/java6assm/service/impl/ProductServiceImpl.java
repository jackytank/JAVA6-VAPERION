package com.edu.java6assm.service.impl;

import java.util.List;

import com.edu.java6assm.entity.Product;
import com.edu.java6assm.repository.ProductRepository;
import com.edu.java6assm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository repo;

    @Override
    public List<Product> findAll() {
        return repo.findAll();
    }

    @Override
    public List<Product> findAll(Sort sort) {
        return repo.findAll(sort);
    }

    @Override
    public Product findById(Integer id) {
        return repo.findById(id).get();
    }

    @Override
    public List<Product> findByCategoryId(Integer cid) {
        return repo.findByCategoryId(cid);
    }

    @Override
    public Product create(Product product) {
        return repo.save(product);
    }

    @Override
    public Product update(Product product) {
        return repo.save(product);
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public List<Product> searchProducts(String query) {
        List<Product> products = repo.searchProducts(query);
        return products;
    }

}
