package todobeast.exceptions;

public class InvalidCommandException extends ToDoBeastException {
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}
