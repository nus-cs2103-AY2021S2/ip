package duke.exceptions;

/**
 * Signals errors related to Duke chat bot.
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
