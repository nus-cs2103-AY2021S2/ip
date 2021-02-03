package duke.command;

import duke.util.TaskStorage;
import duke.util.Ui;
import duke.exception.DukeStorageException;
import duke.task.Task;
import duke.util.TaskList;

/**
 * Class representing an Add command in Duke.
 */
public class AddCommand extends Command {
    private Task toAdd;

    /**
     * Constructor of AddCommand.
     *
     * @param toAdd The task to be added to the list of tasks.
     */
    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }

    /**
     * Executes the add command.
     * Adds new task to existing list of tasks.
     * Updates the task storage of new task.
     * Prompts the user that the task has been added and show the total number of tasks.
     *
     * @param tasks List of tasks.
     * @param ui Formats message and prints it to user.
     * @param storage Updates tasks.txt of the new task.
     * @return true.
     */
    public String execute(TaskList tasks, Ui ui, TaskStorage storage) {
        try {
            tasks.addTask(toAdd);
            storage.storeData(tasks);
            return ui.formatAddCmdMsg(toAdd, tasks);
        } catch (DukeStorageException e) {
            return e.getMessage();
        }
    }
}
