package Command;
import Oracle.TaskList;
import Oracle.Ui;
import Entry.Task;

public class DeleteCommand implements Command {
    private final int taskIndex;

    /**
     * @param i index of the task to be deleted
     */
    public DeleteCommand(int i) {
        this.taskIndex = i;
    }

    /**
     * @param ui helper to print the new deleted task
     * @param tasks we call delete on the indicated task in this TaskList
     * @return true
     */
    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        try {
            Task t = tasks.get(this.taskIndex);
            tasks.remove(this.taskIndex);
            ui.showDeleteTask(this.taskIndex, t);
        } catch (IndexOutOfBoundsException e) {
            ui.showIndexException(tasks.size(), "tasks");
        }
        return true;
    }
}
