package com.tanboonji.jhin.exception;

/**
 * The InvalidDateTimeException class is a custom exception for the invalid date time in the Jhin application.
 */
public class InvalidDateTimeException extends JhinException {

    /**
     * Class constructor specifying the error message for the exception.
     *
     * @param message Error message for exception.
     */
    public InvalidDateTimeException(String message) {
        super(message);
    }
}
