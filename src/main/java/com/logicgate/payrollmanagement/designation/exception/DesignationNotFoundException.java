package com.logicgate.payrollmanagement.designation.exception;

public class DesignationNotFoundException extends RuntimeException {
    public DesignationNotFoundException() {
        super();
    }

    public DesignationNotFoundException(String message) {
        super(message);
    }
}
