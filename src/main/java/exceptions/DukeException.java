package exceptions;

/**
 *  Generic DukeException.
 *  Occurs when duke.Duke-related exceptions happens.
 *
 *  @author Yap Jing Kang
 */

public abstract class DukeException extends Exception {
    public abstract String getMessage();
}
