package Duke.Exception;

/**
 * An exception that handle commands that are not available in the command list.
 */
public class NoSuchCommandException extends Exception {

    /**
     * Gets the message that user sees when entering a command that is not in the command list.
     * @return A message to the user.
     */
    @Override
    public String getMessage() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
