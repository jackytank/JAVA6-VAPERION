package com.edu.java6assm.controller.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.java6assm.entity.Role;
import com.edu.java6assm.service.RoleService;

import java.util.List;

@CrossOrigin("*")
@RestController
public class RoleRestController {
    @Autowired
    RoleService roleService;

    @GetMapping("/rest/roles")
    public List<Role> getAll(){
        return roleService.findAll();
    }

    
}