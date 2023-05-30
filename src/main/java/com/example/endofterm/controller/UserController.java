package com.example.endofterm.controller;

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

    @GetMapping("/selectAllUser")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/selectById/{UserId}")
    public User getUserById(@PathVariable("UserId") Integer userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/selectByName/{username}")
    public User getUserByName(@PathVariable String username) {
        return userService.getUserByName(username);
    }

    @GetMapping("/selectByCondition")
    public List<User> getUserByCondition(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "identity", required = false) Integer identity) {
        User user = new User(id, username, identity);
        return userService.getUserByCondition(user);
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody @NotNull User user) {
        if (user.getUsername() != (null) && user.getPassword() != null && user.getIdentity() != null
                && user.getUsername() != "" && user.getPassword() != "") {
            if (getUserByName(user.getUsername()) == null) {
                int result = userService.addUser(user);
                if (result > 0) {
                    return "{\n" +
                            "\t\"message\": \"添加成功\",\n" +
                            "\t\"success\": \"ture\"\n" +
                            "}";
                }
            } else {
                return "{\n" +
                        "\t\"message\": \"添加失败，用户名已被注册，请重新输入用户名\",\n" +
                        "\t\"success\": \"false\"\n" +
                        "}";
            }
        }

        return "{\n" +
                "\t\"message\": \"添加失败，请完善用户注册信息\",\n" +
                "\t\"success\": \"false\"\n" +
                "}";
    }

    @DeleteMapping("/deleteUser/{userId}")
    public String deleteUserById(@PathVariable Integer userId) {
        boolean flag = getUserById(userId).getIdentity() == 0 ? true : false;
        if (!flag) {
            int result = userService.deleteUserById(userId);
            if (result>0) {
                return "{\"message\":\"删除成功\",\"success\":\"true\"}";
            }else {
                return "{\"message\":\"删除失败\",\"success\":\"false\"}";
            }
        }
        return "{\"message\":\"删除失败，该用户是管理员，无法删除\",\"success\":\"false\"}";
    }

    @PatchMapping("/updateUser/{userId}")
    public String updateUser(@PathVariable Integer userId, @RequestBody @NotNull Map<String, String> userMap) {
        User user = new User();
        if (userMap.get("username") != null) user.setUsername(userMap.get("username"));
        if (userMap.get("password") != null) user.setPassword(userMap.get("password"));
        if (userMap.get("identity") != null) user.setIdentity(Integer.parseInt(userMap.get("identity")));
        if (userMap.get("sum") != null) user.setSum(Integer.parseInt(userMap.get("sum")));
        if (userMap.get("noBack") != null) user.setNoBack(Integer.parseInt(userMap.get("noBack")));
//        System.out.println(user);
        if (getUserById(userId) != null) {
            int result = userService.updateUser(userId, user);
            if (result > 0) {
                return "{\n" +
                        "\t\"message\": \"修改成功\",\n" +
                        "\t\"success\": \"ture\"\n" +
                        "}";
            }
            return "{\n" +
                    "\t\"message\": \"修改失败\",\n" +
                    "\t\"success\": \"false\"\n" +
                    "}";
        }
        return "{\n" +
                "\t\"message\": \"修改失败,没有该用户\",\n" +
                "\t\"success\": \"false\"\n" +
                "}";
    }

}
