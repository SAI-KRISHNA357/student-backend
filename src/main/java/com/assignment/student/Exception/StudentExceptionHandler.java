package com.assignment.student.Exception;

import com.assignment.student.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class StudentExceptionHandler {

    // Invalid Argument Exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleGlobalException(MethodArgumentNotValidException ex) {

        ObjectError error =  ex.getBindingResult().getAllErrors().get(0);

        String exceptionMessage = String.format("Invalid : %s - %s",
                ((org.springframework.validation.FieldError) error).getField(),
                error.getDefaultMessage());

        Response errorResponse = Response.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(exceptionMessage)
                .timestamp(LocalDateTime.now())
                .build();
        log.error("[Exception-Handler] Illegal Argument Exception Response : {}", errorResponse);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Handle general exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleGlobalException(Exception ex) {

        Response errorResponse = Response.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        log.error("[Exception-Handler] General Exception Response : {}", errorResponse);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
