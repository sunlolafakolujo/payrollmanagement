package com.logicgate.payrollmanagement.pensionmanager.exception;

public class PensionAdministratorNotFoundException extends RuntimeException{
    public PensionAdministratorNotFoundException() {
        super();
    }

    public PensionAdministratorNotFoundException(String message) {
        super(message);
    }
}
