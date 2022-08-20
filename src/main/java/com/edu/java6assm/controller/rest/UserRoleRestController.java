package com.edu.java6assm.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.java6assm.model.UserRoleCount;
import com.edu.java6assm.service.UserRoleService;

@CrossOrigin("*")
@RestController
public class UserRoleRestController {
    @Autowired
    UserRoleService service;

    @GetMapping("/rest/usersrole/count")
    public List<UserRoleCount> getAll() {
        return service.getUserRoleCounts();
    }

}
