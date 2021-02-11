/**
 * Customised Duke exception to be thrown when Duke related problems arise.
 */
public class DukeException extends Exception {
    /**
     * Constructor for the DukeException.
     * @param message Message to be shown when the DukeException is caught.
     */
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "OOPS! " + super.getMessage();
    }
}
