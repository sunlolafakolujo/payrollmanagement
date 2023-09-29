package com.logicgate.payrollmanagement.salary.exception;

public class SalaryNotFoundException extends RuntimeException{
    public SalaryNotFoundException() {
        super();
    }

    public SalaryNotFoundException(String message) {
        super(message);
    }
}
