package com.test.userstore.processors;

import com.test.userstore.exceptions.ValidationException;

import java.io.IOException;

public interface CommandProcessor {

    void process() throws IOException, ValidationException;
}
