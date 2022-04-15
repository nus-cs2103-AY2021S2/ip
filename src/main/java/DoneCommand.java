/**
 * Represents a command involving the completion of a task.
 */
public class DoneCommand extends Command {
    private int taskNo;

    /**
     * Constructor for DoneCommand.
     * @param taskNo Number corresponding to the completed task.
     */
    DoneCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.set(this.taskNo - 1, tasks.get(this.taskNo - 1).done());
        storage.saveTasks(tasks);
        return ui.done(tasks.get(this.taskNo - 1).toString());
    }

    /**
     * Checks the equivalence of DoneCommand this and Object obj.
     * If obj is an instance of the DoneCommand class and all attributes are equivalent,
     * it is equivalent to this.
     * @param obj the object which will be compared to this.
     * @return Indication of whether obj is equivalent to this.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof DoneCommand) {
            DoneCommand doneCommand = (DoneCommand) obj;
            return doneCommand.taskNo == this.taskNo;
        }
        return false;
    }
}
