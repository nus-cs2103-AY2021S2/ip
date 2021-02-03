package com.weiliang;

/**
 * Main wrapper class for Duke exceptions
 */
public class DukeException extends Exception {

    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor for DukeException
     * 
     * @param message the description of the exception
     */
    public DukeException(String message) {
        super(message);
    }

}
