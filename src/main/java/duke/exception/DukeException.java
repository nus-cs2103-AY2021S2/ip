package duke.exception;

/**
 *  Generic DukeException.
 *  Occurs when duke.Duke-related duke.exceptions happens.
 *
 *  @author Yap Jing Kang
 */

public abstract class DukeException extends Exception {
    public abstract String getMessage();
}
