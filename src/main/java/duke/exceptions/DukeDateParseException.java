package duke.exceptions;

/**
 * A exception that is thrown when Duke encounters an error while parsing dates in Date Parser.
 */
public class DukeDateParseException extends Exception {
    public DukeDateParseException(String message) {
        super(message);
    }

}
