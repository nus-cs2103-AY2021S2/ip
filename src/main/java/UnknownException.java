/*
 * A type of DukeException.
 * An exception for the rest of the cases.
 */
public class UnknownException extends DukeException {
    public UnknownException(String error) {
        super(error);
    }
}