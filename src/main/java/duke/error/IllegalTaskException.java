package duke.error;

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
        return "You forgot to enter the description of the " + taskType + "!\n" + "Please re-enter!";
    }
}
