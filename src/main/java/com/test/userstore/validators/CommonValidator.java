package com.test.userstore.validators;

import com.test.userstore.exceptions.ValidationException;

public class CommonValidator implements Validator<String> {

    @Override
    public String validate(String value) throws ValidationException {
        if(value == null || value.isEmpty()){
            throw new ValidationException("Shouldn't be empty");
        }
        return value;
    }
}
