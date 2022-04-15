/**
 * Exception when there is an IO Exception.
 */
public class DukeIoException extends Exception {
    /**
     * Constructs a Duke IO Exception.
     * @param message Takes in a String error message.
     */
    public DukeIoException(String message) {
        super(message);
    }

    /**
     * Overrides Exception's toString method.
     * @return Returns the original message passed in.
     */
    @Override
    public String toString() {
        return super.getMessage();
    }
}
