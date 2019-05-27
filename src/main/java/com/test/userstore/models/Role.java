package com.test.userstore.models;

public enum Role {

    USER,
    GUEST,
    ADMIN,
    MANAGER;

    public static Role from(String value) {
        for (Role role : Role.values()) {
            if (role.name().equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException();
    }

}
