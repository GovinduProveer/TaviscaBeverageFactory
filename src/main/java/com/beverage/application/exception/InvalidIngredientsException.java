package com.beverage.application.exception;

public class InvalidIngredientsException extends RuntimeException {

    public InvalidIngredientsException(String msg) {
        super(msg);
    }
}
