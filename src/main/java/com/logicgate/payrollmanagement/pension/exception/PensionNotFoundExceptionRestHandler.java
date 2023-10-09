package com.logicgate.payrollmanagement.pension.exception;

import com.logicgate.payrollmanagement.errormessage.ValidateErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class PensionNotFoundExceptionRestHandler extends ResponseEntityExceptionHandler {
    @ResponseStatus
    @ExceptionHandler(PensionNotFoundException.class)
    public ResponseEntity<ValidateErrorMessage> pensionNotFoundException(PensionNotFoundException pnfe, WebRequest request) {
        ValidateErrorMessage vem = new ValidateErrorMessage(HttpStatus.NOT_FOUND,
                pnfe.getMessage(),
                request.getDescription(false),
                new Date());
        return new ResponseEntity<>(vem, HttpStatus.BAD_REQUEST);
    }
}
