package com.os.service.domain.exception;

public class OrderFinishedOrCanceledException extends RuntimeException {
    public OrderFinishedOrCanceledException(String message) {
        super(message);
    }
}
