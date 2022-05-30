package com.asdasd.mjeesh.store.exception_heandling;

import com.asdasd.mjeesh.store.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> noSuchEntity(EntityNotFoundException exception) {
        return buildResponseEntity(new ErrorData(HttpStatus.NOT_FOUND, exception));
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorData errorData) {
        return new ResponseEntity<>(errorData, errorData.getStatus());
    }
}
