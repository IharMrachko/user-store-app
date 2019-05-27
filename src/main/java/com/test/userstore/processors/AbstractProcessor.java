package com.test.userstore.processors;

import com.test.userstore.exceptions.ValidationException;
import com.test.userstore.models.User;
import com.test.userstore.models.UserField;
import com.test.userstore.service.FieldValidator;

import java.util.List;
import java.util.Scanner;

public abstract class AbstractProcessor implements CommandProcessor {

    protected User fillUser(User user, Scanner scanner) {

        List<UserField> fields = FieldValidator.getFields();

        int index = 0;

        while (index < fields.size()) {
            UserField field = fields.get(index);
            System.out.print(field.name().replace("_", " ") + ": ");
            String line = scanner.nextLine();
            try {
                FieldValidator.validateAndSet(user, field, line);
                ++index;
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
            }
        }
        return user;
    }
}
