package duke.exception;

/**
 * Encapsulates information of potential exceptions in Duke.
 */
public class DukeException extends Exception {

    /**
     * Initialises a new exception specific to Duke chatbot.
     */
    public DukeException(String message) {
        super(message);
    }
}
