package com.test.userstore.processors;

import com.test.userstore.models.User;
import com.test.userstore.service.UserObjectMapper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;
import java.util.UUID;

public final class SaveProcessor extends AbstractProcessor {

    @Override
    public void process() throws IOException {

        User user = super.fillUser(new User(), new Scanner(System.in));

        user.setId(UUID.randomUUID().toString());
        String stringUser = UserObjectMapper.convertToString(user);

        Writer output = new BufferedWriter(new FileWriter("json_users.json", true));
        output.append(stringUser).append("\n");
        output.close();
    }
}
