package com.kchmurowicz.todolist.exception;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorInfo {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime timestamp;
    private String message;
    private HttpStatus status;
    private String error;
    private String path;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ErrorInfo(String message, HttpStatus status, String error, String path) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.status = status;
        this.error = error;
        this.path = path;
    }
}
