package chip.exceptions;

/**
 * Exception thrown when input is invalid.
 */
public class InvalidInputException extends ChipException {
    public InvalidInputException() {
        super("Please enter a valid index :(");
    }

    public InvalidInputException(int maxIdx) {
        super("Please enter a valid index between 1 and " + maxIdx + " :(");
    }
}
