package duke;

public class DukeException extends Exception {
    /**
     * This constructor returns a Duke Exception
     * @param message Error Message
     * @return a Duke Exception
     */
    public DukeException(String message) {
        super(message);
    }
    public DukeException() {
        super();
    }
}
