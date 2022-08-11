package com.edu.java6assm.controller.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.java6assm.entity.User;
import com.edu.java6assm.service.UserService;

@CrossOrigin("*")
@RestController
public class UserRestController {
    @Autowired
    UserService userService;

    @GetMapping("/rest/users")
    public List<User> getAccounts(@RequestParam("admin") Optional<Boolean> admin) {
        if (admin.orElse(false)) {
            return userService.getAdministators();
        }
        return userService.findAll();
    }

    @GetMapping("/rest/users/{username}")
    public User getUserByUsername(@PathVariable("username") Optional<String> username) {
        return userService.findByUsername(username.get()).get();
    }
}