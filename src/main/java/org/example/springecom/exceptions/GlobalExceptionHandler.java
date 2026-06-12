package org.example.springecom.exceptions;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<?> handleEmailAlreadyExists(EmailAlreadyExistException e){
        return ResponseEntity.status(409).body(Map.of("message",e.getMessage()));
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> HandleUserNotFound(UsernameNotFoundException e){
        System.out.println(e.getMessage());
        return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
    }
}
