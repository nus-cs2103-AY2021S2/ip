package chip.exceptions;


/**
 * Exception thrown when date is invalid.
 */
public class InvalidDateException extends ChipException {
    public InvalidDateException(String invalidDate) {
        super(invalidDate + " is not a valid date. Please try again :(");
    }
}
