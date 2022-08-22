package com.edu.java6assm.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    @GetMapping("/rest/users/principal")
    public ResponseEntity<Object> getAuthenticatedUser(Authentication authentication) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Optional<User> loggedinUser = userService.findByUsername(authentication.getName());
            map.put("id", loggedinUser.get().getId());
            map.put("username", loggedinUser.get().getUsername());
            map.put("phone", loggedinUser.get().getPhone());
            map.put("email", loggedinUser.get().getEmail());
            map.put("image_url", loggedinUser.get().getImage_url());
            return ResponseEntity.ok(map);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
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
            // List<SimpleGrantedAuthority> huhu = user.get().getAuthorities().stream()
            // .map(au -> new SimpleGrantedAuthority("ROLE_" + au.getRole().getId()))
            // .peek(System.out::println)
            // .collect(Collectors.toList());
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
            @RequestBody User editUser) throws JsonProcessingException {
        try {
            Optional<User> existingUser = userService.findByUsername((String) idOrUsername.get());
            if (!existingUser.isPresent()) {
                existingUser = userService.findById(Integer.valueOf((String) idOrUsername.get()));
            }
            if (!existingUser.get().getProvider().equals(AuthProvider.DATABASE)) {
                return ResponseEntity.badRequest().body(editUser);
            }

            // BeanUtils.copyProperties(editUser, existingUser);
            if (editUser.getEnabled() == null) {
                editUser.setEnabled(true);
            }
            if (editUser.getProvider() != AuthProvider.DATABASE) {
                editUser.setProvider(AuthProvider.DATABASE);
            }
            ;
            System.out
                    .println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(editUser)
                            + "\n\n\n");
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