package com.kcc.restfulservice.exception;

// 2XX -> ok
// 3XX -> redirect
// 4XX -> client error
// 5XX -> server error

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 404 error
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
