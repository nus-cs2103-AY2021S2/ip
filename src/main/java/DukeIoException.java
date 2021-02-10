/**
 * Exception when there is an IO Exception.
 */
public class DukeIoException extends Exception {
    /**
     * Constructor method for Duke's Missing Input Exception.
     * @param message Takes in a String message.
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
