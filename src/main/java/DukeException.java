/**
 * Exception class to denote errors arising from incorrect user input or
 * failed task list file reading.
 */
public class DukeException extends Exception {

    /**
     * Creates new DukeException with a description.
     *
     * @param message Describes the error.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Creates a new DukeException without a description. Used as a placeholder for
     * another class to create a new DukeException that can describe the error better.
     */
    public DukeException() {}

    /**
     * Returns a String describing the error to the user.
     *
     * @return String describing the error.
     */
    public String errorMessage() {
        String output = this.getMessage();
        return output;
    }
}

