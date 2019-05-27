package com.test.userstore.processors;

import com.test.userstore.exceptions.ValidationException;
import com.test.userstore.service.UserObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class GetOneProcessor implements CommandProcessor {

    @Override
    public void process() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("INTER USER ID:");
        String userId = scanner.nextLine();

        Files.lines(Paths.get("json_users.json"))
                .map(UserObjectMapper::convert)
                .filter(user -> userId.equals(user.getId()))
                .findFirst()
                .map(user -> {
                    System.out.println(UserObjectMapper.convertToString(user));
                    return user;
                })
                .orElseGet(() -> {
                    System.out.println("User doesn't exist");
                    return null;
                });
    }
}
