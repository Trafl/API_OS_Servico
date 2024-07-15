package com.os.service.domain.exception;

public class ServiceInOrderNotFoundException extends RuntimeException {
    public ServiceInOrderNotFoundException(String message) {
        super(message);
    }
}
