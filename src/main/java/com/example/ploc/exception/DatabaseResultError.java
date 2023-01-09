package com.example.ploc.exception;

public class DatabaseResultError extends RuntimeException{
    public DatabaseResultError() {
        super();
    }

    public DatabaseResultError(String message) {
        super(message);
    }

    public DatabaseResultError(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseResultError(Throwable cause) {
        super(cause);
    }

    protected DatabaseResultError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
