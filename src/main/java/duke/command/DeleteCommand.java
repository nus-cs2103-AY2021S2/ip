package duke.command;

import duke.TaskList;
import duke.TaskStorage;
import duke.Ui;
import duke.task.Task;

/**
 * Class representing a Delete Command.
 */
public class DeleteCommand extends Command {
    private int indexToDelete;

    /**
     * Constructor of DeleteCommand.
     * @param indexToDelete The index of the task to be deleted.
     */
    public DeleteCommand(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }

    /**
     * Executes the delete command.
     * Removes task from list of tasks.
     * Updates the task storage.
     * Prompts the user that the task has been removed and show the total number of tasks.
     * @param tasks List of tasks.
     * @param ui Formats and prints message to user.
     * @param storage Updates tasks.txt of the removed task.
     */
    public void execute(TaskList tasks, Ui ui, TaskStorage storage) {
        Task removedTask = tasks.deleteTask(indexToDelete);
        storage.storeData(tasks);
        ui.print("I've removed this task:\n\t\t" + removedTask +
                "\n\n\t  You have " +
                tasks.getSize() + (tasks.getSize() == 1 ? " task" : " tasks") + " in your list");
    }
}
