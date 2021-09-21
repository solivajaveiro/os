package com.soliva.os.services.exceptions;

public class DataIntegratyViolationException extends RuntimeException {

    public DataIntegratyViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataIntegratyViolationException(String cause) {
        super(cause);
    }
}
