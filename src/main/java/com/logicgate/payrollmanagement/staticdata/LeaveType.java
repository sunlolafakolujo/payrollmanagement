package com.logicgate.payrollmanagement.staticdata;

public enum LeaveType {
    ANNUAL("Annual"),
    SICK("Sick"),
    STUDY("Study");

    private final String leaveType;

    LeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getLeaveType() {
        return leaveType;
    }
}
