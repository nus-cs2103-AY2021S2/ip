package duke;

/**
 * Represents a Duke exception that is thrown during an error.
 */
public class DukeException extends Exception {
    DukeException(String errorMessage) {
        super("☹ Oopsie Doopsies!!! " + errorMessage);
    }
}