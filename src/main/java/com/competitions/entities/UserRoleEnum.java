package com.competitions.entities;

public enum UserRoleEnum {

    GUEST("Guest"),
    MEMBER("Member"),
    CAPTAIN("Captain"),
    LEAD("Lead");

    private final String displayValue;

    UserRoleEnum(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}