package com.edu.java6assm.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.edu.java6assm.entity.User;
import com.edu.java6assm.entity.UserRole;

public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    public CustomUserDetails() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Code theo kiểu truyền thống - cách mới
        // List<UserRole> roles = user.getAuthorities();
        // List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        // for (UserRole role : roles) {
        // authorities.add(new SimpleGrantedAuthority("ROLE_" +
        // role.getRole().getId()));
        // }
        // return authorities;

        // Code theo kiểu Stream và Lamdba - cách cũ (ko xài nhưng comment lại nữa tham
        // khảo)
        // return account.getAuthorities().stream()
        // .map(au -> new SimpleGrantedAuthority(au.getName()))
        // .collect(Collectors.toSet());

        // Code theo kiểu Stream và Lamdba - cách mới (nên xài vì code gọn)
        return user.getAuthorities().stream()
                .map(au -> new SimpleGrantedAuthority("ROLE_" + au.getRole().getId()))
                .peek(System.out::println) // println ra để debug (có thể xóa)
                .collect(Collectors.toList());
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
