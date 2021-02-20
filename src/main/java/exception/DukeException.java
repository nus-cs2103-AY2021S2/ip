package exception;

/**
 * public class DukeException
 * extends Exception
 *
 * DukeException is a form of Throwable that indicates conditions that a reasonable application might want to catch.
 */

public class DukeException extends Exception {

    /**
     * Constructs a new DukeException with the specified detail message.
     * @param message DukeException error message
     */
    public DukeException(String message) {
        super(message);
    }

}
