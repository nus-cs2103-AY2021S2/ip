package fakebot.command;

/**
 * Command Exception Class
 */
public class CommandException extends Exception {

    /**
     * Class constructor specifying the error message.
     */
    public CommandException(String errorMessage) {
        super(errorMessage);
    }
}
