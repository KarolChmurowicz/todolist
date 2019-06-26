package com.kchmurowicz.todolist.service;

import com.kchmurowicz.todolist.models.User;
import com.kchmurowicz.todolist.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        LOGGER.debug("creating a User");
        return userRepository.save(user);
    }

    public List<User> findAll() {
        LOGGER.debug("getting a list of User");
        return userRepository.findAll();
    }

    public void delete(Long userId) {
        LOGGER.debug("deleting a User");
        userRepository.deleteById(userId);
    }

    public User update(User user) {
        LOGGER.debug("updating a User");
        return userRepository.save(user);
    }

    public Optional<User> findById(Long userId) {
        LOGGER.debug("getting a User with specific id");
        return userRepository.findById(userId);
    }
}
