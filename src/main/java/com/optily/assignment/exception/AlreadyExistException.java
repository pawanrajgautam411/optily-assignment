package com.optily.assignment.exception;

/**
 *
 */
public class AlreadyExistException extends RuntimeException {

    /**
     *
     */
    public AlreadyExistException() {
    }

    /**
     * @param message
     */
    public AlreadyExistException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public AlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
