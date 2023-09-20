package com.logicgate.payrollmanagement.state.exception;

public class StateNotFoundException extends RuntimeException{
    public StateNotFoundException() {
        super();
    }

    public StateNotFoundException(String message) {
        super(message);
    }
}
