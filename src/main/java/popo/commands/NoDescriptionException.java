package popo.commands;

/**
 * Signals an empty description.
 */
public class NoDescriptionException extends Exception {

    public NoDescriptionException(String errorMessage) {
        super(errorMessage);
    }
}
