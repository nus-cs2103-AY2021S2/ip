package com.nus.duke.common;

/**
 * DukeException class Indicates an error during Duke Application runtime.
 */
public class DukeException extends Exception {

    public DukeException(String description) {
        super(description);
    }
}
