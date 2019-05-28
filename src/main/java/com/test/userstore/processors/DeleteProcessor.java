package com.test.userstore.processors;

import com.test.userstore.models.User;
import com.test.userstore.service.UserObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DeleteProcessor implements CommandProcessor {

    private final UserObjectMapper filteWriter = new UserObjectMapper();

    @Override
    public void process() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("INTER USER ID:");
        String userId = scanner.nextLine();

        Path path = Paths.get("json_users.json");

        List<String> users = Files.lines(path).collect(Collectors.toList());

        Integer index = null;
        for (int i = 0; i < users.size(); i++) {
            User user = filteWriter.convert(users.get(i));
            if (userId.equals(user.getId())) {
                index = i;
                break;
            }
        }

        if (index != null) {
            users.remove(index.intValue());
        }
        Files.write(path, users);
    }
}
