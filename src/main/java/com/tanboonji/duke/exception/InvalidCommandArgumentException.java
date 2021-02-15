package com.tanboonji.duke.exception;

/**
 * The InvalidCommandArgumentException class is a custom exception for invalid command arguments in the Duke
 * application.
 */
public class InvalidCommandArgumentException extends DukeException {

    /**
     * Class constructor specifying the error message for the exception.
     *
     * @param message Error message for exception.
     */
    public InvalidCommandArgumentException(String message) {
        super(message);
    }
}
