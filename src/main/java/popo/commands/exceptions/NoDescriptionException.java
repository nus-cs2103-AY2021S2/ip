package popo.commands.exceptions;

/**
 * Signals an empty description.
 */
public class NoDescriptionException extends Exception {

    public NoDescriptionException(String errorMessage) {
        super(errorMessage);
    }
}
