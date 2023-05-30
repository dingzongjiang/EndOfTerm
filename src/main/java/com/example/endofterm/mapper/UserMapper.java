package com.example.endofterm.mapper;

import com.example.endofterm.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getAllUser();

    int addUser(User user);

    User getUserById(@Param("userId") Integer userId);

    User getUserByName(String username);

    int deleteUserById(Integer userId);

    List<User> getUserByCondition(User user);

    int updateUser(@Param("userId") Integer userId, @Param("user") User user);
}
