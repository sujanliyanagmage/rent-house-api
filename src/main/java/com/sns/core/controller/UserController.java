package com.sns.core.controller;

import com.sns.core.model.User;
import com.sns.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        return this.userService.saveUser(user);
    }

    @GetMapping("/user/me")
    public User getUserDetails(@RequestBody String userName) {
        return this.userService.getUserDetailsByUserName(userName);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<User> getAllUsers() {
        return this.userService.getAll();
    }

}
