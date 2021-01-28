package com.tjtanjin.steve;

/**
 * Class for custom exception handling.
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException class for custom exceptions.
     * @param errMsg error message
     */
    public DukeException(String errMsg) {
        super(errMsg);
    }
}
