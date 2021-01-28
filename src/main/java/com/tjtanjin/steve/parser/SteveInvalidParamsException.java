package com.tjtanjin.steve.parser;

/**
 * Class for custom exception handling.
 */
public class SteveInvalidParamsException extends Exception {

    /**
     * Constructor for DukeException class for custom exceptions.
     * @param errMsg error message
     */
    public SteveInvalidParamsException(String errMsg) {
        super(errMsg);
    }
}
