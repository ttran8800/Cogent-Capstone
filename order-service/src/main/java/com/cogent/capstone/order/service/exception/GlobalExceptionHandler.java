package com.cogent.capstone.order.service.exception;

import com.cogent.capstone.order.service.response.ErrorResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    public ResponseEntity<ErrorResponse> handCustomException(CustomException customException) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(customException.getMessage())
                .details(customException.getErrorCode())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(customException.getStatus()));
    }
}
