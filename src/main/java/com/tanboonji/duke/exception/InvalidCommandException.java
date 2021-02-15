package com.tanboonji.duke.exception;

/**
 * The InvalidCommandException class is a custom exception for invalid commands in the Duke application.
 */
public class InvalidCommandException extends DukeException {

    /**
     * Class constructor specifying the error message for the exception.
     *
     * @param message Error message for exception.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
