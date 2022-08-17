package com.edu.java6assm.controller.rest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.java6assm.entity.User;
import com.edu.java6assm.entity.UserRole;
import com.edu.java6assm.exception.ResourceNotFoundException;
import com.edu.java6assm.model.AuthProvider;
import com.edu.java6assm.service.UserService;

@CrossOrigin("*")
@RestController
public class UserRestController {
    @Autowired
    UserService userService;

    @GetMapping("/rest/users")
    public ResponseEntity<List<User>> getAccounts(@RequestParam("admin") Optional<Boolean> admin) {
        if (admin.orElse(false)) {
            return ResponseEntity.ok(userService.getAdministators());
        }
        return ResponseEntity.ok(userService.findAll());
    }

    // Lí do PathVariable là id hoặc username vì nếu ko tìm thấy bằng id thì sẽ tìm
    // bằng username cho tiện
    @GetMapping("/rest/users/{idOrUsername}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("idOrUsername") Optional<Object> idOrUsername) {
        try {
            Optional<User> user = userService.findByUsername((String) idOrUsername.get());
            if (!user.isPresent()) {
                user = userService.findById(Integer.valueOf((String) idOrUsername.get()));
            }
            return ResponseEntity.ok(user.get());
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/rest/users2/{idOrUsername}")
    public ResponseEntity<List<UserRole>> getUserByUsername2(
            @PathVariable("idOrUsername") Optional<Object> idOrUsername) {
        try {
            Optional<User> user = userService.findByUsername((String) idOrUsername.get());
            if (!user.isPresent()) {
                user = userService.findById(Integer.valueOf((String) idOrUsername.get()));
            }
            List<SimpleGrantedAuthority> huhu = user.get().getAuthorities().stream()
                    .map(au -> new SimpleGrantedAuthority("ROLE_" + au.getRole().getId()))
                    .peek(System.out::println)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(user.get().getAuthorities());
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/rest/users")
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @PutMapping("/rest/users/{idOrUsername}")
    public ResponseEntity<User> update(@PathVariable("idOrUsername") Optional<Object> idOrUsername,
            @RequestBody User editUser) {
        try {
            Optional<User> _user = userService.findByUsername((String) idOrUsername.get());
            if (!_user.isPresent()) {
                _user = userService.findById(Integer.valueOf((String) idOrUsername.get()));
            }
            if (!_user.get().getProvider().equals(AuthProvider.DATABASE)) {
                return ResponseEntity.badRequest().body(editUser);
            }

            User savedUser = userService.update(editUser);
            return ResponseEntity.ok(savedUser);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/rest/users/{username}")
    public void delete(@PathVariable("username") Optional<String> username) {
        try {
            userService.deleteByUsername(username.get());
        } catch (Exception e) {
            throw new ResourceNotFoundException("User", "username", username.get());
        }
    }
}