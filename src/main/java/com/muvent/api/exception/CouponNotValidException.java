package com.muvent.api.exception;

public class CouponNotValidException extends RuntimeException {

    public CouponNotValidException(String message) {
        super(message);
    }
}
