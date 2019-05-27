package com.test.userstore.validators;

import com.test.userstore.exceptions.ValidationException;
import com.test.userstore.models.Role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RoleValidator implements Validator<List<Role>> {

    @Override
    public List<Role> validate(String value) throws ValidationException {
        String validatedValue = new CommonValidator().validate(value);

        Set<Role> roles = null;
        try {

            roles = Arrays.stream(validatedValue.split("\\|"))
                    .filter(it -> !it.isEmpty())
                    .map(String::trim)
                    .map(Role::from)
                    .collect(Collectors.toSet());

        } catch (Exception e) {
            String collect = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.joining(" | "));
            throw new ValidationException("Max role count is 3. And any of values " + collect  + ". Use | to split roles");
        }

        if (roles.size() > 3) {
            throw new ValidationException("Max role count is 3");
        }

        return new ArrayList<>(roles);
    }
}
