package com.logicgate.payrollmanagement.grantorloan.exception;

public class GrantLoanNotFoundException extends RuntimeException{
    public GrantLoanNotFoundException() {
        super();
    }

    public GrantLoanNotFoundException(String message) {
        super(message);
    }
}
