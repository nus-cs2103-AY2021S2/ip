package duke.exception;


/**
 * It is a new exception class extends from Exception for the Duke program.
 * It receives the exceptions during the running of the program and
 * presents to the user if needed.
 */
public class DukeException extends Exception {
    public DukeException(){}
    public DukeException(String message) {
        super(message);
    }
}
