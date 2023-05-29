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
}
