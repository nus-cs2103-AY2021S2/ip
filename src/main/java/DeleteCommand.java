/**
 * Represents a command involving the deletion of a task.
 */
public class DeleteCommand extends Command {
    private int taskNo;

    /**
     * Constructor for DeleteCommand.
     * @param taskNo Number corresponding to the task to be deleted.
     */
    DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    String execute(TaskList tasks, Ui ui, Storage storage) {
        String task = tasks.get(this.taskNo - 1).toString();
        tasks.remove(this.taskNo - 1);
        storage.saveTasks(tasks);
        return ui.delete(task, tasks.size());
    }

    /**
     * Checks the equivalence of DeleteCommand this and Object obj.
     * If obj is an instance of the DeleteCommand class and all attributes are equivalent,
     * it is equivalent to this.
     * @param obj the object which will be compared to this.
     * @return Indication of whether obj is equivalent to this.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof DeleteCommand) {
            DeleteCommand deleteCommand = (DeleteCommand) obj;
            return deleteCommand.taskNo == this.taskNo;
        }
        return false;
    }
}
