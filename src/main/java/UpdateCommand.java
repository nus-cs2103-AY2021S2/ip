/**
 * Represents a command involving the editing of a task.
 */
public class UpdateCommand extends Command {
    private int taskNo;
    private String date;

    /**
     * Constructor for UpdateCommand.
     * @param taskNo Number corresponding to task to be edited.
     * @param date Date for edited task.
     */
    UpdateCommand(int taskNo, String date) {
        this.taskNo = taskNo;
        this.date = date;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(this.taskNo - 1).set(this.date);
        tasks.set(this.taskNo - 1, task);
        storage.saveTasks(tasks);
        return ui.updateTask(task.toString());
    }

    /**
     * Checks the equivalence of UpdateCommand this and Object obj.
     * If obj is an instance of the UpdateCommand class and all attributes are equivalent,
     * it is equivalent to this.
     * @param obj the object which will be compared to this.
     * @return Indication of whether obj is equivalent to this.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof UpdateCommand) {
            UpdateCommand updateCommand = (UpdateCommand) obj;
            return updateCommand.taskNo == this.taskNo
                    && updateCommand.date.equals(this.date);
        }
        return false;
    }
}
