package com.muvent.api.exception;

public class EventNotFoundException extends RuntimeException {

    public EventNotFoundException(String message) {
        super(message);
    }
}
