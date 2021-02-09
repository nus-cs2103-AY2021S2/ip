/**
 * Invalid Command Exception class thrown when the user types in an invalid command
 */
public class InvalidCommandException extends Exception {
    /**
     *
     * @return the invalid command message
     */
    @Override
    public String getMessage() {
        return "Command is not recognized";
    }
}
