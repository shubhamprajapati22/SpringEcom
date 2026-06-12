package org.example.springecom.exceptions;

public class EmailAlreadyExistException extends RuntimeException {
    public EmailAlreadyExistException(String email) {
        super(email + " Already exists");
    }
}
