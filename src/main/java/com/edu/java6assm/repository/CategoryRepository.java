package com.edu.java6assm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.java6assm.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,String>{
    
}
