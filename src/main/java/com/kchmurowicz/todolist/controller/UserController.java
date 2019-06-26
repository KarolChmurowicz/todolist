package com.kchmurowicz.todolist.controller;

import com.kchmurowicz.todolist.models.User;
import com.kchmurowicz.todolist.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public @ResponseBody
    List<User> getUserList() {
        LOGGER.debug("Received a request to get a list of User");
        return userService.findAll();
    }

    @PostMapping
    public @ResponseBody
    User createUser(@RequestBody User user) {
        LOGGER.debug("Received a request to create a User");
        return userService.save(user);
    }

    @DeleteMapping
    public void deleteUser(@RequestParam(name = "id") Long userId) {
        LOGGER.debug("Received a request to delete a User");
        userService.delete(userId);
    }

    @PutMapping
    public @ResponseBody
    User updateUser(@RequestBody User user) {
        LOGGER.debug("Received a request to update a User");
        return userService.update(user);
    }
}


