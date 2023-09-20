package com.logicgate.payrollmanagement.staticdata;

public enum AccountType {
    SAVINGS("Savings"),
    CURRENT("Current"),
    ;

    private final String accountType;

    AccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountType() {
        return accountType;
    }
}
