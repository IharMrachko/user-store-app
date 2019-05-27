package com.test.userstore.validators;

import com.test.userstore.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PhoneValidator implements Validator<List<String>> {

    private final Pattern pattern =
            Pattern.compile("^375[0-9]{2} [0-9]{3}[0-9]{2}[0-9]{2}$");

    @Override
    public List<String> validate(String value) throws ValidationException {
        String validatedValue = new CommonValidator().validate(value);

        Set<String> collect = Arrays.stream(validatedValue.split("\\|"))
                .filter(it -> !it.isEmpty())
                .map(String::trim)
                .collect(Collectors.toSet());

        boolean isNotValid = collect.stream().anyMatch(x -> !pattern.matcher(x).matches());

        if (isNotValid) {
            throw new ValidationException("Not supported phone format. Should be 375xx xxxxxxx");
        }

        if (collect.size() > 3) {
            throw new ValidationException("Max role count is 3. Use | to split phones");
        }

        return new ArrayList<>(collect);
    }
}
