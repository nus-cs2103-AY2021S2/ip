package duke.exceptions;

/**
 * An error that is thrown when Duke encounters error parsing the command in CommandParser.
 */
public class DukeCommandParseException extends Exception {
    public DukeCommandParseException(String errorMessage) {
        super(errorMessage);
    }
}
