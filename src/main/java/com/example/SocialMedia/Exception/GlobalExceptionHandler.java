package com.example.SocialMedia.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Indexed;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> ExceptionHandler(Exception e, WebRequest req){
        ErrorDetails err=ErrorDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(req.getDescription(false))
                .data(e.getMessage())
                .build();
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
