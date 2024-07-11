package com.os.service.domain.exception;

public class GroupServiceNotFoundException extends RuntimeException {
    public GroupServiceNotFoundException(String message) {
        super(message);
    }
}
