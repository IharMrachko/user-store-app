package com.test.userstore.validators;

import com.test.userstore.exceptions.ValidationException;

import java.util.regex.Pattern;

public class EmailValidator implements Validator<String> {

    private final Pattern pattern =
            Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    @Override
    public String validate(String value) throws ValidationException {
        if (!pattern.matcher(value).matches()) {
            throw new ValidationException("Invalid email");
        }
        return value;
    }
}
