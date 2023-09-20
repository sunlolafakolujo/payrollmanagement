package com.logicgate.payrollmanagement.allowance.exception;

public class AllowanceNotFoundException extends RuntimeException {
    public AllowanceNotFoundException() {
        super();
    }

    public AllowanceNotFoundException(String message) {
        super(message);
    }
}
