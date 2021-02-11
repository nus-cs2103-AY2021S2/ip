package Duke.Exception;

/**
 * An exception for handling invalid index (negative or out of bound) for done and delete command.
 */
public class InvalidIndex extends IndexOutOfBoundsException {
    private final String type;
    private final int listSize;

    /**
     * This exception constructor has 2 parameters: the type of the command (done or delete) and the number of tasks in the list.
     * @param type The type of the command (done or delete).
     * @param listSize The number of tasks in the list.
     */
    public InvalidIndex(String type, int listSize) {
        this.type = type;
        this.listSize = listSize;
    }

    /**
     * Gets the message that user sees when entering an invalid index.
     * @return A message to the user.
     */
    @Override
    public String getMessage() {
        return type + " command should be followed by a number between 1 and " + listSize + ".";
    }
}
