package yoda.command;

/**
 * InvalidCommandFormatException class that throws an exception when invalid arguments are given for commands.
 */
public class InvalidCommandFormatException extends Exception {
    /**
     * Creates an InvalidCommandFormatException exception.
     * @param error The error the user made when entering their command.
     */
    public InvalidCommandFormatException(String error) {
        super(error);
    }
}
