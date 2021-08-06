package com.example.mongodb.user.application;

import com.example.mongodb.user.application.dto.UserDto;
import com.example.mongodb.user.domain.User;
import com.example.mongodb.user.domain.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    public UserDto getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException());
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto save(UserDto userDto) {
        User user = userRepository.save(new User(userDto.getName(), userDto.getUserSettings()));
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto update(String userId, String name) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException());

        user.nameUpdate(name);
        userRepository.save(user);

        return modelMapper.map(user, UserDto.class);
    }

    public Object getAllUserSettings(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException());

        return user.getUserSettings();

    }

    public String getUserSetting(String userId, String key) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException());

        return user.getUserSettings().get(key);
    }

    public void addUserSetting(String userId, String key, String value) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException());

        user.getUserSettings().put(key, value);
        userRepository.save(user);
    }

}
