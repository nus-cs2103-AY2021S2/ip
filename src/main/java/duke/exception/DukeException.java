package duke.exception;

/**
 *  Generic DukeException.
 *  Occurs when Duke-related exceptions occur.
 *
 *  @author Yap Jing Kang
 */

public abstract class DukeException extends Exception {
    public abstract String getMessage();
}
