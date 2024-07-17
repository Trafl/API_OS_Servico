package com.os.service.domain.exception;

public class OrderWrongStatusException extends RuntimeException {
    public OrderWrongStatusException(String message) {
        super(message);
    }
}
