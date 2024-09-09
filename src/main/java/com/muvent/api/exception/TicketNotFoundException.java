package com.muvent.api.exception;

public class TicketNotFoundException extends RuntimeException {

    public TicketNotFoundException(String message) {
        super(message);
    }
}
