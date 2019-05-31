package com.kchmurowicz.todolist.controller;

import com.kchmurowicz.todolist.models.User;
import com.kchmurowicz.todolist.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public @ResponseBody
    List<User> getUserList() {
        return userService.findAll();
    }

    @PostMapping
    public @ResponseBody User createUser(@RequestBody User user){
        return userService.save(user);
    }

    @DeleteMapping
    public void deleteUser(@RequestParam(name = "id") Long userId){
        userService.delete(userId);
    }

    @PutMapping
    public @ResponseBody User updateUser(@RequestBody User user){
        return userService.update(user);
    }
}


