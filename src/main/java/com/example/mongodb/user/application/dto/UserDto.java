package com.example.mongodb.user.application.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class UserDto {

    private String name;

    private Date creationDate = new Date();

    private Map<String, String> userSettings = new HashMap<>();


}
