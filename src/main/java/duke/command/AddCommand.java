package duke.command;

import duke.TaskStorage;
import duke.Ui;
import duke.task.Task;
import duke.TaskList;

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
    public boolean execute(TaskList tasks, Ui ui, TaskStorage storage) {
        tasks.addTask(toAdd);
        storage.storeData(tasks);
        ui.print("Got it. I've added this task:\n\t\t" + toAdd +
                "\n\n\t  You have " +
                tasks.getSize() + (tasks.getSize() == 1 ? " task" : " tasks") + " in your list");
        return true;
    }
}
