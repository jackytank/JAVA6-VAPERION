package com.edu.java6assm.security;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edu.java6assm.entity.User;
import com.edu.java6assm.exception.ResourceNotFoundException;
import com.edu.java6assm.repository.UserRepository;

@Service

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("User not found with username : " + username));
        return user.map(CustomUserDetails::new).get();
    }

    // @Transactional
    // public UserDetails loadUserById(Integer id) {
    //     Optional<User> user = userRepo.findById(id);
    //     user.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    //     return user.map(CustomUserDetails::new).get();
    // }

}
