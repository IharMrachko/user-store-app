package com.test.userstore.service;

import com.test.userstore.exceptions.ValidationException;
import com.test.userstore.models.Role;
import com.test.userstore.models.User;
import com.test.userstore.models.UserField;
import com.test.userstore.validators.CommonValidator;
import com.test.userstore.validators.EmailValidator;
import com.test.userstore.validators.PhoneValidator;
import com.test.userstore.validators.RoleValidator;
import com.test.userstore.validators.Validator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

public final class FieldValidator {

    private final static Map<UserField, Validator> validators = new LinkedHashMap<>();
    private final static Map<UserField, BiConsumer> consumer = new LinkedHashMap<>();

    static {
        CommonValidator commonValidator = new CommonValidator();
        validators.put(UserField.FIRST_NAME, commonValidator);
        validators.put(UserField.LAST_NAME, commonValidator);
        validators.put(UserField.EMAIL, new EmailValidator());
        validators.put(UserField.ROLES, new RoleValidator());
        validators.put(UserField.PHONES, new PhoneValidator());

        consumer.put(UserField.FIRST_NAME, ((BiConsumer<User, String>) User::setFirstName));
        consumer.put(UserField.LAST_NAME, ((BiConsumer<User, String>) User::setLastName));
        consumer.put(UserField.EMAIL, ((BiConsumer<User, String>) User::setEmail));
        consumer.put(UserField.ROLES, ((BiConsumer<User, List<Role>>) User::setRoles));
        consumer.put(UserField.PHONES, ((BiConsumer<User, List<String>>) User::setPhones));
    }

    @SuppressWarnings("unchecked")
    public static void validateAndSet(User u, UserField field, String value) throws ValidationException {
        Object validatedValue = validators.get(field).validate(value);
        consumer.get(field).accept(u, validatedValue);
    }

    public static List<UserField> getFields() {
        return new ArrayList<>(validators.keySet());
    }
}
