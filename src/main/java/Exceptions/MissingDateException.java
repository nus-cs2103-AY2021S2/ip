package Exceptions;

import Exceptions.DukeException;

public class MissingDateException extends DukeException {
    public MissingDateException(String message) {
        super("☹ OOPS!!! Please enter a date/time.");
    }
}
