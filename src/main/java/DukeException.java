/**
 * Exception class to denote errors arising from incorrect user input or
 * failed task list file reading.
 */
public class DukeException extends Exception {

    /** States if the error is isUnknown */
    private boolean isUnknown;

    /**
     * Creates new DukeException with a description.
     *
     * @param message Describes the error.
     */
    public DukeException(String message) {
        super(message);
        if (message.equals("unknown")) {
            isUnknown = true;
        } else {
            isUnknown = false;
        }
    }

    /**
     * Returns a String describing the error to the user.
     *
     * @return String describing the error.
     */
    public String errorMessage() {
        if (isUnknown) {
            return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        } else if (this.getMessage().equals("loading error")) {
            return "☹ OOPS!!! No file found.";
        } else if (this.getMessage().equals("delete")) {
            return "☹ OOPS!!! You must state which task to delete.";
        } else if (this.getMessage().equals("search")) {
            return "☹ OOPS!!! You must give a keyword.";
        } else {
            return "☹ OOPS!!! The description of a " + this.getMessage() + " cannot be empty.";
        }
    }
}
