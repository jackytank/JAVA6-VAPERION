package com.edu.java6assm.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.edu.java6assm.entity.UserRole;
import com.edu.java6assm.service.UserRoleService;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
public class AuthorityRestController {
    @Autowired
    UserRoleService userRoleService;

    @GetMapping("/rest/authorities")
    public List<UserRole> findAll(@RequestParam("admin") Optional<Boolean> admin) {
        if (admin.orElse(false)) {
            return userRoleService.findRolesOfAdministrators();
        }
        return userRoleService.findAll();
    }

    @PostMapping("/rest/authorities")
    public UserRole post(@RequestBody UserRole userRole) {
        return userRoleService.create(userRole);
    }

    @DeleteMapping("/rest/authorities/{id}")
    public void delete(@PathVariable("id") Integer id) {
        userRoleService.delete(id);
    }
}
