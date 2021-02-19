package com.tanboonji.jhin.exception;

/**
 * The InvalidCommandException class is a custom exception for invalid commands in the Jhin application.
 */
public class InvalidCommandException extends JhinException {

    /**
     * Class constructor specifying the error message for the exception.
     *
     * @param message Error message for exception.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
