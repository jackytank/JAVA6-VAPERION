package com.edu.java6assm.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.edu.java6assm.model.AuthProvider;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String phone;

    // đặt tên là image_url vì khi user login bằng GG,FB thì lưu url ảnh
    // user signup theo dạng database thì chỉ lưu tên ảnh <tên>.jpg
    private String image_url;

    @Enumerated(EnumType.STRING) // Đinh dạng cái enum AuthProvider như là String
    private AuthProvider provider;
    private String provider_id;
    private Boolean enabled;
    private String verify_code;
    private String reset_pwd_token;

    // OneToMany cách mới của Codejava.com - Chạy được nhưng khi update User thì bên
    // UserRole sẽ bị xóa chứ ko cascade

    // @JsonIgnore
    // @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // @JoinTable(name = "users_role", joinColumns = @JoinColumn(name = "user_id"),
    // inverseJoinColumns = @JoinColumn(name = "role_id"))
    // private Set<Role> authorities = new HashSet<>();

    // OneToMany cách cũ của thầy Nghiệm - HIỆU QUẢ
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Order> orders;
    
    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserRole> authorities;

}
