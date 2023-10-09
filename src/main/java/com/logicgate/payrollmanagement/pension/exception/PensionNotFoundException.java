package com.logicgate.payrollmanagement.pension.exception;

public class PensionNotFoundException extends RuntimeException{
    public PensionNotFoundException() {
        super();
    }

    public PensionNotFoundException(String message) {
        super(message);
    }
}
