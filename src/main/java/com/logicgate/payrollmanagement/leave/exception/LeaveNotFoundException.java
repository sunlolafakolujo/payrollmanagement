package com.logicgate.payrollmanagement.leave.exception;

public class LeaveNotFoundException extends RuntimeException{
    public LeaveNotFoundException() {
        super();
    }

    public LeaveNotFoundException(String message) {
        super(message);
    }
}
