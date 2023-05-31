package com.example.endofterm.controller;

import com.example.endofterm.pojo.Role;
import com.example.endofterm.pojo.User;
import com.example.endofterm.serivce.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAllUser")
    public List<User> getAllUsers() {
        return userService.getAllUser();
    }

    @GetMapping("/getUserById")
    public User getUserById(@RequestParam("userId") Integer userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/login")
    public String login(@RequestBody @NotNull Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        User user = userService.getUserByName(username);
        if (user == null) {
            return "{\"message\":\"登陆失败，没有该用户\",\"success\":\"true\"}";
        } else if (!user.getPassword().equals(password)) {
            return "{\"message\":\"登陆失败，密码错误\",\"success\":\"true\"}";
        } else {
            return "{\"message\":\"登陆成功\",\"success\":\"true\"}";
        }
    }

    @PostMapping("/register")
    public String register(@RequestBody @NotNull Map<String, String> map) {
        User user = new User();
        if(map.get("username").equals("") || map.get("password").equals("") || map.get("identity").equals("")
                || map.get("username") == null || map.get("password") == null || map.get("identity") == null){
            return "{\"message\":\"注册失败，信息不完整\",\"success\":\"true\"}";
        }
        user.setUsername(map.get("username"));
        user.setPassword(map.get("password"));
        user.setIdentity(Integer.parseInt(map.get("identity")));
        System.out.println(user);
        if (userService.getUserByName(user.getUsername()) != null) {
            return "{\"message\":\"注册失败，用户名已存在\",\"success\":\"true\"}";
        } else {
            int result = userService.addUser(user);
            if (result > 0) {
                return "{\"message\":\"注册成功\",\"success\":\"true\"}";
            }
            return "{\"message\":\"注册失败\",\"success\":\"true\"}";
        }
    }

}
