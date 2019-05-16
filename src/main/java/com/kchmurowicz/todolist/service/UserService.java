package com.kchmurowicz.todolist.service;

import com.kchmurowicz.todolist.models.User;
import com.kchmurowicz.todolist.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();

    }

    public void delete(Long userId ){
        userRepository.deleteById(userId);

    }
    public User update(User user){
        return userRepository.save(user);
    }

    public Optional<User> findById(Long userId){
        return userRepository.findById(userId);
    }

}
