package com.logicgate.payrollmanagement.staticdata;

public enum GrantOrLoanType {
    HOUSING("Housing"),
    CAR("Car");

    public final String grantOrLoanType;

    GrantOrLoanType(String grantOrLoanType) {
        this.grantOrLoanType = grantOrLoanType;
    }

    private String getGrantOrLoanType() {
        return grantOrLoanType;
    }
}
