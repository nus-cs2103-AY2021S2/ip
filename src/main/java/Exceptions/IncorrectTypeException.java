package Exceptions;

import Exceptions.DukeException;

public class IncorrectTypeException extends DukeException {
    public IncorrectTypeException(String message) {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
