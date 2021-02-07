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
     * Executes the command.
     * @param tasks The task list used for execution of the command.
     * @param ui Interactions with users.
     * @param storage Data stored in the local file path.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        storage.updateFile();
        return ui.showAdded(task, tasks);
    }

    /**
     * Returns a boolean value to signal the chat bot to exit.
     * @return False as the command does not signal the bot to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
