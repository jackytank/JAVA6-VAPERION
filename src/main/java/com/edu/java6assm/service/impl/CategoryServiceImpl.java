package com.edu.java6assm.service.impl;

import java.util.List;

import com.edu.java6assm.entity.Category;
import com.edu.java6assm.repository.CategoryRepository;
import com.edu.java6assm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository repo;

    @Override
    public List<Category> findAll() {
        return repo.findAll();
    }

}
