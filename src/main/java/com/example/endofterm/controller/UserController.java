package com.example.endofterm.controller;

import com.example.endofterm.pojo.User;
import com.example.endofterm.serivce.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public static User getUserByMap(Map<String, Object> map) {
        User user = new User();
        if (map.get("id") != null)
            user.setId(Integer.parseInt(map.get("id").toString()));
        if (map.get("username") != null)
            user.setUsername(map.get("username").toString());
        if (map.get("password") != null)
            user.setPassword(map.get("password").toString());
        if (map.get("identity") != null)
            user.setIdentity(Integer.parseInt(map.get("identity").toString()));
        if (map.get("sum") != null)
            user.setSum(Integer.parseInt(map.get("sum").toString()));
        if (map.get("noBack") != null)
            user.setNoBack(Integer.parseInt(map.get("noBack").toString()));
        return user;
    }

    //查询所有用户
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userService.getAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    //按id查询用户
    @GetMapping("/getUserById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    //按用户名查询用户
    @GetMapping("/getUserByName/{username}")
    public ResponseEntity<User> getUserByName(@PathVariable("username") String username) {
        User user = userService.getUserByName(username);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    //按条件查询用户
    @GetMapping("/getUserByCondition")
    public ResponseEntity<List<User>> getUserByCondition(@RequestBody Map<String, Object> map) {
        User user = getUserByMap(map);
        List<User> userList = userService.getUserByCondition(user);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    //登录
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @NotNull User userForm) {
        User user = userService.getUserByName(userForm.getUsername());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"用户不存在\",\"success\":\"false\"}");
        } else if (!user.getPassword().equals(userForm.getPassword())) {
            return new ResponseEntity<>("{\"message\":\"密码错误\",\"success\":\"false\"}", HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>("{\"message\":\"登陆成功\",\"success\":\"true\"}", HttpStatus.OK);
        }
    }

    //注册
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @NotNull User userForm) {
        if(userForm.getUsername()==null||userForm.getUsername()=="")
            return new ResponseEntity<>("{\"message\":\"注册失败，用户名不能为空\",\"success\":\"false\"}", HttpStatus.BAD_REQUEST);
        if(userForm.getPassword()==null||userForm.getPassword()=="")
            return new ResponseEntity<>("{\"message\":\"注册失败，密码不能为空\",\"success\":\"false\"}", HttpStatus.BAD_REQUEST);
        if (userForm.getIdentity()==null||userForm.getIdentity()==0)
            return new ResponseEntity<>("{\"message\":\"注册失败，身份不能为空\",\"success\":\"false\"}", HttpStatus.BAD_REQUEST);
        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setPassword(userForm.getPassword());
        user.setIdentity(userForm.getIdentity());
        System.out.println(user);
        if (userService.getUserByName(user.getUsername()) != null) {
            return new ResponseEntity<>("{\"message\":\"注册失败，用户名已存在\",\"success\":\"false\"}", HttpStatus.BAD_REQUEST);
        } else {
            int result = userService.addUser(user);
            if (result > 0) {
                return new ResponseEntity<>("{\"message\":\"注册成功\",\"success\":\"true\"}", HttpStatus.CREATED);
            }
            return new ResponseEntity<>("{\"message\":\"注册失败\",\"success\":\"false\"}", HttpStatus.BAD_REQUEST);
        }
    }

    //按id删除用户
    @DeleteMapping("/deleteUserById/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer userId, @RequestParam("operatorId") Integer operatorId) {
        User operator = userService.getUserById(operatorId);
        if (operator.getIdentity() == 0) {
            User user = userService.getUserById(userId);
            if (user != null) {
                int result = userService.deleteUserById(userId);
                if (result > 0) {
                    return new ResponseEntity<>("{\"message\":\"删除成功\",\"success\":\"true\"}", HttpStatus.OK);
                }
                return new ResponseEntity<>("{\"message\":\"删除失败\",\"success\":\"false\"}", HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>("{\"message\":\"删除失败，没有该用户\",\"success\":\"false\"}", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("{\"message\":\"删除失败，权限不足\",\"success\":\"false\"}", HttpStatus.FORBIDDEN);
    }

    //按id修改用户
    @PatchMapping("/updateUser/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable Integer userId, @RequestBody Map<String,Object>map) {
        User user = getUserByMap(map);
        User userTarget = userService.getUserById(userId);
        if (userTarget != null) {
            int result = userService.updateUser(userId, user);
            if (result > 0) {
                return new ResponseEntity<>("{\"message\":\"修改成功\",\"success\":\"true\"}", HttpStatus.OK);
            }
            return new ResponseEntity<>("{\"message\":\"修改失败\",\"success\":\"false\"}", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("{\"message\":\"修改失败，没有该用户\",\"success\":\"false\"}", HttpStatus.BAD_REQUEST);
        }
    }
}
