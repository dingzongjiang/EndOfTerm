package com.example.endofterm.controller;

import com.example.endofterm.pojo.Role;
import com.example.endofterm.pojo.User;
import com.example.endofterm.serivce.UserService;
import org.apache.ibatis.annotations.Param;
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

    //查询所有用户
    @GetMapping("/getAllUser")
    public List<User> getAllUsers() {
        return userService.getAllUser();
    }

    //按id查询用户
    @GetMapping("/getUserById")
    public User getUserById(@RequestParam("userId") Integer userId) {
        return userService.getUserById(userId);
    }

    //按用户名查询用户
    @GetMapping("/getUserByName")
    public User getUserByName(@RequestParam("username") String username) {
        return userService.getUserByName(username);
    }

    //按条件查询用户
    @GetMapping("/getUserByCondition")
    public List<User> getUserByCondition(@RequestBody User user) {
        return userService.getUserByCondition(user);
    }

    //登录
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

    //注册
    @PostMapping("/register")
    public String register(@RequestBody @NotNull Map<String, String> map) {
        User user = new User();
        if (map.get("username").equals("") || map.get("password").equals("") || map.get("identity").equals("")
                || map.get("username") == null || map.get("password") == null || map.get("identity") == null) {
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

    //按id删除用户
    @DeleteMapping("/deleteUserById/{operatorId}")
    public String deleteUserById(@PathVariable Integer operatorId, @RequestParam("userId") Integer userId) {
        User operator = userService.getUserById(operatorId);
        if (operator.getIdentity() == 0) {
            User user = userService.getUserById(userId);
            if (user != null) {
                int result = userService.deleteUserById(userId);
                if (result > 0) {
                    return "{\"message\":\"删除成功\",\"success\":\"true\"}";
                }
                return "{\"message\":\"删除失败\",\"success\":\"true\"}";
            } else {
                return "{\"message\":\"删除失败，没有该用户\",\"success\":\"true\"}";
            }
        }
        return "{\"message\":\"删除失败，你的权限不够\",\"success\":\"true\"}";
    }

    //按id修改用户
    @PatchMapping("/updateUser/{userId}")
    public String updateUser(@PathVariable Integer userId, @RequestBody User user) {
        User user1 = userService.getUserById(userId);
        if (user1 != null) {
            int result = userService.updateUser(userId, user);
            if (result > 0) {
                return "{\"message\":\"修改成功\",\"success\":\"true\"}";
            }
            return "{\"message\":\"修改失败\",\"success\":\"true\"}";
        } else {
            return "{\"message\":\"修改失败，没有该用户\",\"success\":\"true\"}";
        }
    }
}
