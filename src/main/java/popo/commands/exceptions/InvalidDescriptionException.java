package popo.commands.exceptions;

/**
 * Signals an invalid description for the command.
 */
public class InvalidDescriptionException extends Exception {

    public InvalidDescriptionException(String errorMessage) {
        super(errorMessage);
    }
}
