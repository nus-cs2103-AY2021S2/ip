package duke.error;

/**
 * Exception thrown when 'todo', 'deadline' or 'event' input is incorrectly formatted.
 *
 * @author  Nicole Ang
 */
public class IllegalTaskException extends StringIndexOutOfBoundsException {
    protected String taskType;

    /**
     * Constructs a new IllegalTaskException.
     *
     * @param message  Exception message.
     * @param taskType Type of the task, either "todo", "deadline" or "event".
     */
    public IllegalTaskException(String message, String taskType) {
        super(message);
        this.taskType = taskType;
    }

    /**
     * Returns error message telling user that they did not enter the task description and to re-enter the input.
     *
     * @return Error message.
     */
    @Override
    public String toString() {
        return "Your input for the " + taskType + " task is not formatted correctly!\n" + "Please re-enter!";
    }
}
