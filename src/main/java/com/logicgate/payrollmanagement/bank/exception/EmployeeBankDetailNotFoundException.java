package com.logicgate.payrollmanagement.bank.exception;

public class EmployeeBankDetailNotFoundException extends RuntimeException{
    public EmployeeBankDetailNotFoundException() {
        super();
    }

    public EmployeeBankDetailNotFoundException(String message) {
        super(message);
    }
}
