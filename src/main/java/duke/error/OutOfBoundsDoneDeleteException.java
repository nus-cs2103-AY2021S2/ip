package duke.error;

/**
 * Exception thrown when task to delete or mark as done does not exist.
 *
 * @author  Nicole Ang
 */
public class OutOfBoundsDoneDeleteException extends IndexOutOfBoundsException {
    /**
     * Constructs a new OutOfBoundsDoneDeleteException.
     *
     * @param message  Exception message.
     */
    OutOfBoundsDoneDeleteException(String message) {
        super(message);
    }

    /**
     * Returns error message telling user that the task they entered is not on the list and to re-enter the input.
     *
     * @return Error message.
     */
    @Override
    public String toString() {
        return "The task you entered does not exist!\n" + "Please re-enter!";
    }
}
