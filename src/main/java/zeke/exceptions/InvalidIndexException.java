package zeke.exceptions;

public class InvalidIndexException extends ZekeException {

    public InvalidIndexException() {
    }

    @Override
    public String getMessage() {
        return "MonkaS! The index given is out of range and invalid.\n";
    }
}
