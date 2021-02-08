package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Handles the logic of marking a task in the tasks list as done.
 */
public class DoneCommand extends Command {
    private final String description;

    /**
     * Initializes a command to mark a task as done. The task to mark as done will be
     * identified using its index in the application's <code>TaskList</code>.
     *
     * @param description Index of the task to mark as done.
     */
    public DoneCommand(String description) {
        this.description = description;
    }

    /**
     * Mark a <code>Task</code> in the input <code>TaskList</code>, identified by its index, as done.
     * Then, prints responses to notify the users.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     * @param ui A handler to manage the application's user-interface layer.
     */
    public void execute(TaskList tasks, Ui ui) {
        Task doneTask = tasks.getTaskByIndex(Integer.parseInt(description));
        if (null != doneTask) {
            doneTask.markAsDone();
            ui.handleDone(doneTask);
        }
    }

    public boolean isExit() {
        return false;
    }
}
