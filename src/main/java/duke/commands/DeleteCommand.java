package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Handles the logic of deleting a task from the tasks list.
 */
public class DeleteCommand extends Command {
    private final String description;

    /**
     * Initializes a command to delete a task. The task to delete will be identified using
     * its index in the application's <code>TaskList</code>.
     *
     * @param description Index of the task to delete.
     */
    public DeleteCommand(String description) {
        this.description = description;
    }

    /**
     * Deletes a <code>Task</code>, identified by its index, from the input <code>TaskList</code>.
     * Then, prints responses to notify the users.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     * @param ui A handler to manage the application's user-interface layer.
     */
    public void execute(TaskList tasks, Ui ui) {
        Task deletedTask = tasks.popTaskByIndex(Integer.parseInt(description));
        if (null != deletedTask) {
            ui.handleDelete(deletedTask);
        }
    }

    public boolean isExit() {
        return false;
    }
}
