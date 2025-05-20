package com.uca.hrm.comm.exception;

public class InvalidException extends RuntimeException {

    public InvalidException(String message) {
        super(message);
    }

    public InvalidException(String message, Throwable cause) {
        super(message, cause);
    }

}
