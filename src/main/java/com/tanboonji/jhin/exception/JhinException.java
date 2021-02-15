package com.tanboonji.jhin.exception;

/**
 * The JhinException class is a custom exception for the Jhin application.
 */
public class JhinException extends Exception {

    /**
     * Class constructor specifying the error message for the exception.
     *
     * @param message Error message for exception.
     */
    public JhinException(String message) {
        super(message);
    }
}
