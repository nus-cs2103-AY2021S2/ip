package duke;

public class DukeException extends Exception {
    private String message;

    public DukeException() {
        super();
    }

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
