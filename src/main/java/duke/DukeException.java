package duke;

/**
 * Represents an Exception that is thrown
 * when user enters invalid commands and/or
 * arguments.
 */
public class DukeException extends Exception {

    /**
     * Class constructor.
     * @param errorMessage details of the invalid command or argument.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

}