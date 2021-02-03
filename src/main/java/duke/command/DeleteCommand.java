package duke.command;

import duke.util.TaskList;
import duke.util.TaskStorage;
import duke.util.MessageFormatter;
import duke.exception.DukeStorageException;
import duke.task.Task;

/**
 * Class representing a Delete Command.
 */
public class DeleteCommand extends Command {
    private int indexToDelete;

    /**
     * Constructor of DeleteCommand.
     *
     * @param indexToDelete The index of the task to be deleted.
     */
    public DeleteCommand(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }

    /**
     * Executes the delete command.
     * Removes task from list of tasks.
     * Updates the task storage.
     * Prompts the user that the task has been removed and shows the total number of tasks.
     *
     * @param tasks List of tasks.
     * @param messageFormatter Formats Duke's response into a String.
     * @param storage Updates tasks.txt of the removed task.
     * @return Duke's response after deleting task from the list.
     */
    public String execute(TaskList tasks, MessageFormatter messageFormatter, TaskStorage storage) {
        try {
            Task taskRemoved = tasks.deleteTask(indexToDelete);
            storage.storeData(tasks);
            return messageFormatter.formatRemoveCmdMsg(taskRemoved, tasks);
        } catch (DukeStorageException e) {
            return e.getMessage();
        }
    }
}
