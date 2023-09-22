package com.logicgate.payrollmanagement.userrole.exception;

public class RoleNotFoundException extends RuntimeException{
    public RoleNotFoundException() {
        super();
    }

    public RoleNotFoundException(String message) {
        super(message);
    }
}
