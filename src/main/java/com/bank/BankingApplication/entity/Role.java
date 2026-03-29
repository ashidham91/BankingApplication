package com.bank.BankingApplication.entity;

public enum Role {
    ROLE_CUSTOMER("User"),
    ROLE_ADMIN("Admin");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
