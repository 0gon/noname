package com.uca.hrm.comm.exception;

public class DomainInvalidException extends RuntimeException {

    public DomainInvalidException(String message) {
        super(message);
    }

    public DomainInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

}
