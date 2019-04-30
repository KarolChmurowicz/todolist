package com.kchmurowicz.todolist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorInfo> illegalArgumentExceptionHandler(IllegalArgumentException ex, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(new ErrorInfo(ex.getMessage(), HttpStatus.BAD_REQUEST, ex.getClass().toString(), request.getServletPath()));
    }
}
