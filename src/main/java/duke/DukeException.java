package duke;

public class DukeException extends Exception {
    protected static final String HELP_MSG = "     Please check format/command via 'help'.\n";

    /**
     * Returns string for DukeException.
     * @return String message for a DukeException.
     */
    @Override
    public String toString() {
        return "Exception in Duke occurred!";
    }
}
