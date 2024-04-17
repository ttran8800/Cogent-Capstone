package com.cogentcapstone.product.service.exceptions;

import com.cogentcapstone.product.service.payload.ErrorDetailPayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class HttpGlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetailPayload> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest){

        ErrorDetailPayload errorPayload = new ErrorDetailPayload(
                new Date(),
                ex.getMessage(),
                webRequest.getDescription(true)
        );
        return new ResponseEntity<>(errorPayload, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientQuantityException.class)
    public ResponseEntity<ErrorDetailPayload> handleInsufficientQuantityException(InsufficientQuantityException ex, WebRequest webRequest) {

        ErrorDetailPayload errorPayload = new ErrorDetailPayload(
                new Date(),
                ex.getMessage(),
                webRequest.getDescription(true)
        );
        return new ResponseEntity<>(errorPayload, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
