package com.logicgate.payrollmanagement.jobgroup.exception;

public class JobGroupNotFoundException extends RuntimeException {
    public JobGroupNotFoundException() {
        super();
    }

    public JobGroupNotFoundException(String message) {
        super(message);
    }
}
