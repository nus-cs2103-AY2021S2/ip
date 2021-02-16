/**
 * Exception when there is a missing input from user.
 */
public class DukeMissingInputException extends Exception {
    /**
     * Constructs Duke's Missing Input Exception.
     * @param message Takes in a String message.
     */
    public DukeMissingInputException(String message) {
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
