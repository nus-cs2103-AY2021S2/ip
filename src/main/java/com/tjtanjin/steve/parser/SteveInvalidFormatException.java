package com.tjtanjin.steve.parser;

/**
 * Class for custom exception handling of invalid input format.
 */
public class SteveInvalidFormatException extends Exception {

    /**
     * Constructor for SteveInvalidFormatException class for custom exceptions
     * of invalid input format.
     *
     * @param errMsg error message
     */
    public SteveInvalidFormatException(String errMsg) {
        super(errMsg);
    }
}
