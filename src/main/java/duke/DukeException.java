package duke;

public class DukeException extends Exception {
    /**
     * Constructs a DukeException object.
     * @param e Error message
     */
    public DukeException(String e) {
        super("Oops! " + e);
    }

}
