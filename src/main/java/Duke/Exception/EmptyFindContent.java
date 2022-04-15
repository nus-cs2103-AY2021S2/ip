package Duke.Exception;

/**
 * An exception for Find command without the content.
 */
public class EmptyFindContent extends Exception {
    /**
     * Gets the message that user sees when entering a find command with no content.
     * @return A message to the user.
     */
    @Override
    public String getMessage() {
        return "Find command must follow by a keyword!";
    }
}
