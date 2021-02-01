package exception;

/**
 * Duke date format exception.
 */
public class DukeDateFormatException extends DukeException {
    /**
     * Instantiates a new Duke date format exception.
     */
    public DukeDateFormatException() {
        super("Woops! Give me a date like this: [25-10-1997, 10:30PM]");
    }
}
