package com.competitions.entities;

public enum UserRoleEnum {

    ROLE_MEMBER("Member"),
    ROLE_CAPTAIN("Captain"),
    ROLE_LEAD("Lead");

    private final String displayValue;

    UserRoleEnum(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}