package com.logicgate.payrollmanagement.staticdata;

public enum EmployeeStatus {
    REGULAR("Regular"),
    CONTRACT("Contract"),
    INTERN("Intern"),
    RETIRED("Retired"),
    RESIGNED("Resigned"),
    DECEASED("Deceased");

    private final String employeeStatus;

    EmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public String getEmployeeStatus() {
        return employeeStatus;
    }
}
