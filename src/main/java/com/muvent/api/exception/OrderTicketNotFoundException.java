package com.muvent.api.exception;

public class OrderTicketNotFoundException extends RuntimeException {

    public OrderTicketNotFoundException(String message) {
        super(message);
    }
}
