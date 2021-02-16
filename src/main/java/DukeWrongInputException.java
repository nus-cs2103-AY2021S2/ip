/**
 * Exception when there is a wrong input from the user.
 */
public class DukeWrongInputException extends Exception {
    /**
     * Constructs Duke's Wrong Input Exception.
     * @param message Takes in a String message.
     */
    public DukeWrongInputException(String message) {
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
