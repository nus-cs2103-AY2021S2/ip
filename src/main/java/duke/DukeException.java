package duke;

public class DukeException extends Exception {
    private final String message;

    /**
     * Creates an exception using a message.
     *
     * @param message The message to return in toString method.
     */
    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
