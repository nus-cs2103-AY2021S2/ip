package vergil.types.exceptions;

public class VergilIndexException extends VergilException {
    public VergilIndexException() {
        super("I wasn't able to find a task with the specified number.");
    }

    public VergilIndexException(String message) {
        super(message);
    }
}
