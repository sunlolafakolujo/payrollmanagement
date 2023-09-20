package com.logicgate.payrollmanagement.nationality.exception;

public class NationalityNotFoundException extends RuntimeException {
    public NationalityNotFoundException() {
        super();
    }

    public NationalityNotFoundException(String message) {
        super(message);
    }
}
