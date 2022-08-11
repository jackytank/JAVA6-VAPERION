package com.edu.java6assm.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.edu.java6assm.entity.Role;
import com.edu.java6assm.entity.User;

public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    public CustomUserDetails() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Code theo kiểu truyền thống
        Set<Role> roles = user.getAuthorities();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getId()));
        }
        return authorities;
        // Code theo kiểu Stream và Lamdba
        // return account.getAuthorities().stream()
        // .map(au -> new SimpleGrantedAuthority(au.getName()))
        // .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getEnabled();
    }

}
