package duke.command;

/**
 * Completes a task.
 */
public class DoneCommand extends Command {
    private final String taskNumber;

    /**
     * Creates a {@code DoneCommand} object with a task number component only.
     * @param taskNumber
     */
    public DoneCommand(String taskNumber) {
        super("done");
        this.taskNumber = taskNumber;
    }

    /**
     * Returns index of task to be marked as done.
     * @return task index
     */
    public String getTaskNumber() {
        return taskNumber;
    }
}
