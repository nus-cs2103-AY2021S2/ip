/*
 * A type of DukeException.
 * An exception for bad deletions.
 */
public class DeleteException extends DukeException {
    public DeleteException(String error) {
        super(error);
    }
}
