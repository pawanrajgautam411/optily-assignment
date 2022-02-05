package com.optily.assignment.exception;

/**
 *
 */
public class ApplyOptimisationFailedException extends RuntimeException {

    /**
     * @param message
     * @param cause
     */
    public ApplyOptimisationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public ApplyOptimisationFailedException(String message) {
        super(message);
    }
}
