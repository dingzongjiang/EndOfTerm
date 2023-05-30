package com.example.endofterm.serivce;
import com.example.endofterm.pojo.User;
import java.util.List;

public interface UserService {
    List<User> getAllUser();

    int addUser(User user);

    User getUserById(Integer userId);

    User getUserByName(String username);

    int deleteUserById(Integer userId);

    List<User> getUserByCondition(User user);

    int updateUser(Integer userId, User user);
}
