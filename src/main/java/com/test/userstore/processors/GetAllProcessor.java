package com.test.userstore.processors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GetAllProcessor implements CommandProcessor {

    @Override
    public void process() throws IOException {
        Files.lines(Paths.get("json_users.json")).forEach(System.out::println);
    }
}
