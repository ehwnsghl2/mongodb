package com.example.mongodb.user.ui;

import com.example.mongodb.user.application.UserService;
import com.example.mongodb.user.application.dto.UserDto;
import com.example.mongodb.user.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/list")
    public List<UserDto> findAll() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/{userId}")
    public UserDto findOne(@PathVariable String userId) {
        return userService.getUser(userId);
    }

    @PostMapping(value = "/create")
    public UserDto save(@RequestBody UserDto user) {
        return userService.save(user);
    }

    @PutMapping(value = "/update/{userId}")
    public UserDto update(@PathVariable String userId, @RequestBody UserDto userDto) {
        return userService.update(userId, userDto.getName());
    }

    @GetMapping(value = "/settings/{userId}")
    public Object getAllUserSettings(@PathVariable String userId) {
        return userService.getAllUserSettings(userId);
    }


    @GetMapping(value = "/settings/{userId}/{key}")
    public String getUserSetting(@PathVariable String userId, @PathVariable String key) {
        return userService.getUserSetting(userId, key);
    }

    @GetMapping(value = "/settings/{userId}/{key}/{value}")
    public void addUserSetting(@PathVariable String userId, @PathVariable String key, @PathVariable String value) {
        userService.addUserSetting(userId, key, value);
    }


}
