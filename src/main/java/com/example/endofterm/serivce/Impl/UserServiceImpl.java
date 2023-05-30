package com.example.endofterm.serivce.Impl;

import com.example.endofterm.mapper.UserMapper;
import com.example.endofterm.pojo.User;
import com.example.endofterm.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public User getUserById(Integer userId) {
        return userMapper.getUserById(userId);
    }

    @Override
    public User getUserByName(String username) {
        return userMapper.getUserByName(username);
    }

    @Override
    public int deleteUserById(Integer userId) {
        return userMapper.deleteUserById(userId);
    }

    @Override
    public List<User> getUserByCondition(User user) {
        return userMapper.getUserByCondition(user);
    }

    @Override
    public int updateUser(Integer userId, User user) {
        return userMapper.updateUser(userId, user);
    }
}
