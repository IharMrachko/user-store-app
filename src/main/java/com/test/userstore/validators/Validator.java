package com.test.userstore.validators;

import com.test.userstore.exceptions.ValidationException;

public interface Validator<T> {

    T validate(String value) throws ValidationException;

}
