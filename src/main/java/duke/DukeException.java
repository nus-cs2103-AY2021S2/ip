package duke;

/**
 * Represents the custom exceptions used by
 * the Duke chat bot.
 */
public class DukeException extends Exception {

    /**
     * Initialises the user-defined exception
     * with a given message.
     *
     * @param message Exception message.
     */
    public DukeException(String message) {
        super(message);
    }
}

