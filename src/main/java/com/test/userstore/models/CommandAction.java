package com.test.userstore.models;

import com.test.userstore.exceptions.ValidationException;

import java.io.IOException;

public interface CommandAction {

    void doAction() throws IOException, ValidationException;
}
