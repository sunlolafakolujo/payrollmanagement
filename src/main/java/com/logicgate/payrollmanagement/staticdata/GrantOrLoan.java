package com.logicgate.payrollmanagement.staticdata;

public enum GrantOrLoan {
    GRANT("Grant"),
    LOAN("Loan");

    private final String grantOrLoan;

    GrantOrLoan(String grantOrLoan) {
        this.grantOrLoan = grantOrLoan;
    }

    public String getGrantOrLoan() {
        return grantOrLoan;
    }
}
