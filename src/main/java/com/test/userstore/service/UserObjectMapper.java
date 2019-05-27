package com.test.userstore.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.userstore.models.User;

import java.io.IOException;

public class UserObjectMapper {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static User convert(String line) {
        try {
            User user = mapper.readValue(line, User.class);
            return user;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertToString(User user) {
        try {
            return mapper.writeValueAsString(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
