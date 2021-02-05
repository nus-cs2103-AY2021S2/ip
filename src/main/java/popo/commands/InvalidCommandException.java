package popo.commands;

/**
 * Signals an invalid user command.
 */
public class InvalidCommandException extends Exception {

    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}
