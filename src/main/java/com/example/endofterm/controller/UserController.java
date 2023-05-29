package com.example.endofterm.controller;

import com.example.endofterm.pojo.User;
import com.example.endofterm.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/selectAllUser")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }
}
