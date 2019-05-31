package com.kchmurowicz.todolist.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorInfo> illegalArgumentExceptionHandler(IllegalArgumentException ex, HttpServletRequest request) {
        LOGGER.error(ex.getMessage(), ex);
        return ResponseEntity.badRequest().body(new ErrorInfo(ex.getMessage(), HttpStatus.BAD_REQUEST, ex.getClass().toString(), request.getServletPath()));
    }
}
