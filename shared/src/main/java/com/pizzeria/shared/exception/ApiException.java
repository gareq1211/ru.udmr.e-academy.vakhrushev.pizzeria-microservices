package com.pizzeria.shared.exception;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
