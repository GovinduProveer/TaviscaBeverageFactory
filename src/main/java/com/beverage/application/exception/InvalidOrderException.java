package com.beverage.application.exception;

public class InvalidOrderException extends RuntimeException {

    public InvalidOrderException(String msg) {
        super(msg);
    }
}
