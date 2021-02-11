package com.tanboonji.duke.exception;

/**
 * The DukeException class is a custom exception for the Duke application.
 */
public class DukeException extends Exception {

    /**
     * Class constructor specifying the error message for the exception.
     *
     * @param message Error message for exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
