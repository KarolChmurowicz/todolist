package com.kchmurowicz.todolist.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.List;

public class ExtendedUser extends User {
    private Long id;

    public ExtendedUser(Long id, String username, String password, List<GrantedAuthority> roles) {
        super(username, password, roles);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
