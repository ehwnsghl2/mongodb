package com.example.mongodb.user.domain;


import com.example.mongodb.user.domain.User;

import java.util.List;

public interface UserCustomRepository {

    List<User> getAllUsers();

    User getUserById(String userId);

    User addNewUser(User user);

    void nameUpdateUser(String userId, String name);

    Object getAllUserSettings(String userId);

    String getUserSetting(String userId, String key);

    String addUserSetting(String userId, String key, String value);

}
