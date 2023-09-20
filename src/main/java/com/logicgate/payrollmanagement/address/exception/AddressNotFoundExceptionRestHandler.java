package com.logicgate.payrollmanagement.address.exception;

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
public class AddressNotFoundExceptionRestHandler extends ResponseEntityExceptionHandler {
    @ResponseStatus
    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ValidateErrorMessage> addressNotFoundException(AddressNotFoundException anfe, WebRequest request) {
        ValidateErrorMessage vem = new ValidateErrorMessage(HttpStatus.NOT_FOUND,
                anfe.getMessage(),
                request.getDescription(false),
                new Date());
        return new ResponseEntity<>(vem, HttpStatus.BAD_REQUEST);
    }
}
