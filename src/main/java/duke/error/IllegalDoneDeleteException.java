package duke.error;

/**
 * Exception thrown when task to delete or mark as done is not specified.
 *
 * @author  Nicole Ang
 */
public class IllegalDoneDeleteException extends StringIndexOutOfBoundsException {
    protected String taskType;

    /**
     * Constructs a new IllegalDoneDeleteException.
     *
     * @param message  Exception message.
     * @param taskType Type of the task, either "done" or "delete".
     */
    IllegalDoneDeleteException(String message, String taskType) {
        super(message);
        this.taskType = taskType;
    }

    /**
     * Returns error message telling user that they did not enter the task number and to re-enter the input.
     *
     * @return Error message.
     */
    @Override
    public String toString() {
        if (taskType.equals("done")) {
            return "You forgot to enter the task to mark as done!\n" + "Please re-enter!";
        } else {
            return "You forgot to enter the task to delete!\n" + "Please re-enter!";
        }
    }
}
