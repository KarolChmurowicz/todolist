package com.kchmurowicz.todolist.security;

import com.kchmurowicz.todolist.models.User;
import com.kchmurowicz.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {

            throw new UsernameNotFoundException(username);
        }


        return new ExtendedUser(user.getId(), user.getUsername(), user.getPassword(), this.determineUserRoles(user));
    }

    private List<GrantedAuthority> determineUserRoles(User user) {
        List<GrantedAuthority> roles = new ArrayList<>();

        switch (user.getRole()) {
            case ADMIN:
                roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            case USER:
                roles.add(new SimpleGrantedAuthority("ROLE_USER"));
                break;
            default:
                break;
        }

        return roles;
    }

}
