package com.tanboonji.jhin.exception;

/**
 * The InvalidCommandArgumentException class is a custom exception for invalid command arguments in the Jhin
 * application.
 */
public class InvalidCommandArgumentException extends JhinException {

    /**
     * Class constructor specifying the error message for the exception.
     *
     * @param message Error message for exception.
     */
    public InvalidCommandArgumentException(String message) {
        super(message);
    }
}
