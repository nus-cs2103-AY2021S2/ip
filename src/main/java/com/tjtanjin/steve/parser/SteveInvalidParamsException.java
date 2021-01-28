package com.tjtanjin.steve.parser;

/**
 * Class for custom exception handling of invalid params format.
 */
public class SteveInvalidParamsException extends Exception {

    /**
     * Constructor for SteveInvalidParamsException class for custom exceptions
     * of invalid params format.
     * @param errMsg error message
     */
    public SteveInvalidParamsException(String errMsg) {
        super(errMsg);
    }
}
