package monica.command;

import monica.Storage;
import monica.task.Task;
import monica.task.TaskList;
import monica.ui.Ui;

/**
 * Represents a command that adds task into the task list.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor for AddCommand class.
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes add task command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        storage.updateFile();
        return ui.showAdded(task, tasks);
    }
}
