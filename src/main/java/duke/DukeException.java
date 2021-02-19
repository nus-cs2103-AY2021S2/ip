package duke;

public class DukeException extends Exception {
    private String message;

    public DukeException() {
        super();
    }

    /**
     * This creates a DukeException with the specified message.
     *
     * @param message This is the exception message
     */
    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Returns an error message
     *
     * @param message This is the error message
     * @return Returns the error message as a String
     */
    public String printError(String message) {
        return message;
    }

    /**
     * Returns an error message
     *
     * @return Returns the error message as a String
     */
    public String printMessage() {
        return this.message;
    }
}
