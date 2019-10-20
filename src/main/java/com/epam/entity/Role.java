package com.epam.entity;

public enum Role {

    ADMIN, OPERATOR, CLIENT, GUEST;

    public static Role getRole(User user) {
        int roleId = user.getRoleId();
        return Role.values()[roleId];
    }

    public String getName() {
        return name().toLowerCase();
    }
}