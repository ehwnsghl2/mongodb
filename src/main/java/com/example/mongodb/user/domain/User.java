package com.example.mongodb.user.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
public class User {

    @Id
    private String userId;
    private String name;

    private Date creationDate = new Date();

    private Map<String, String> userSettings = new HashMap<>();


    public void nameUpdate(String name) {
        this.name = name;
    }

    public User(String name, Map<String, String> userSettings) {
        this.name = name;
        this.userSettings = userSettings;
    }

}
