package duke.command;

/**
 * Deletes a task.
 */
public class DeleteCommand extends Command {
    private final String taskNumber;

    public DeleteCommand(String taskNumber) {
        super("delete");
        this.taskNumber = taskNumber;
    }

    /**
     * Returns index of task to be deleted.
     * @return task index
     */
    public String getTaskNumber() {
        return taskNumber;
    }
}
