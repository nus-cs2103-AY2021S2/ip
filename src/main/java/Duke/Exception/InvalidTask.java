package Duke.Exception;

<<<<<<< HEAD
/**
 * An exception that handle a command for Deadline command without the word "/by" and Event command without the word "/at".
 */
=======
>>>>>>> branch-A-CodingStandard
public class InvalidTask extends Exception{
    String type;

    /**
     * The constructor for this Exception has 1 parameter: the type of the command (event or deadline).
     * @param type Type of the command (event or deadline).
     */
    public InvalidTask(String type) {
        this.type = type;
    }

    /**
     * Gets the message that user sees when entering a deadline or event without the signature word.
     * @return A message to the user.
     */
    @Override
    public String getMessage() {
        return "Invalid command for " + type + "! Try again";
    }
}
