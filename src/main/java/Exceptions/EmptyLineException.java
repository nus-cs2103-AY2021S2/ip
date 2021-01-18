package Exceptions;

import Exceptions.DukeException;

public class EmptyLineException extends DukeException {
    public EmptyLineException(String message) {
        super("☹ OOPS!!! The input line cannot be empty.");
    }
}
