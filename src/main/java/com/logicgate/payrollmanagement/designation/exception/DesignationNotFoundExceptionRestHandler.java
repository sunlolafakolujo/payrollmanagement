package com.logicgate.payrollmanagement.designation.exception;

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
public class DesignationNotFoundExceptionRestHandler extends ResponseEntityExceptionHandler {
    @ResponseStatus
    @ExceptionHandler(DesignationNotFoundException.class)
    public ResponseEntity<ValidateErrorMessage> designationNotFoundException(DesignationNotFoundException dnfe,
                                                                             WebRequest request) {
        ValidateErrorMessage vem = new ValidateErrorMessage(HttpStatus.NOT_FOUND,
                dnfe.getMessage(),
                request.getDescription(false),
                new Date());
        return new ResponseEntity<>(vem, HttpStatus.BAD_REQUEST);
    }
}
