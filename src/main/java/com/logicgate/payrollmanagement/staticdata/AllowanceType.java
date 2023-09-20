package com.logicgate.payrollmanagement.staticdata;

public enum AllowanceType {
    HOUSING("Housing"),
    MEDICAL("Medical"),
    LUNCH("Lunch"),
    TRANSPORT("Transport"),
    DIESEL("Diesel"),
    BUSH("Bush");

    private final String allowanceType;


    AllowanceType(String allowanceType) {
        this.allowanceType = allowanceType;
    }

    public String getAllowanceType() {
        return allowanceType;
    }
}
