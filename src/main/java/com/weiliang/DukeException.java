package com.weiliang;

/**
 * Main wrapper class for {@link Duke} related exceptions.
 */
public class DukeException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Creates an instance of the Duke exception.
     * @param message The description.
     */
    public DukeException(String message) {
        super(message);
    }

}
