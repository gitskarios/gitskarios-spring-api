package com.alorma.data.exception;

public class UnauthorizedException extends Exception {
    private String message;

    public UnauthorizedException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
