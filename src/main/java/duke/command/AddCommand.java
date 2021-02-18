package duke.command;

import duke.exception.DukeStorageException;
import duke.task.Task;
import duke.util.MessageFormatter;
import duke.util.TaskList;
import duke.util.TaskStorage;

/**
 * Class representing an Add command in Duke.
 */
public class AddCommand extends Command {
    private final Task toAdd;

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
     * @param messageFormatter Formats Duke's response into a String.
     * @param storage Updates tasks.txt of the new task.
     * @return Duke's response after adding task to the list.
     */
    public String execute(TaskList tasks, MessageFormatter messageFormatter, TaskStorage storage) {
        try {
            tasks.addTask(toAdd);
            storage.storeData(tasks);
            return messageFormatter.formatAddMsg(toAdd, tasks);
        } catch (DukeStorageException e) {
            return e.getMessage();
        }
    }
}
