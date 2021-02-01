package duke.exceptions;

/**
 * Represents the exceptions when calling an index more than
 * the list size.
 */
public class IndexOutOfRangeException extends ChatBotException {
    public IndexOutOfRangeException() {
        super("OOPS!!! There are not so many tasks in the list.");
    }
}
