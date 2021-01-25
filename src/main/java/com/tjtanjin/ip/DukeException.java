package com.tjtanjin.ip;

/**
 * Class for custom exception handling.
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException class for custom exceptions.
     * @param errMsg error message to show to user
     */
    public DukeException(String errMsg) {
        super(errMsg);
    }
}
