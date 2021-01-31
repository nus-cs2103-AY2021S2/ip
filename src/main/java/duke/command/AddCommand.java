package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Encapsulates information about a parsed user command.
 */
public class AddCommand extends Command {
    private Task newTask;

    /**
     * @param fullCommand The full user command.
     * @param newTask The new task that will be added in the add command.
     */
    public AddCommand(String fullCommand, Task newTask) {
        super(fullCommand);
        this.newTask = newTask;
    }

    /**
     * Executes the user command to add a new task.
     * @param tasks A TaskList object which encapsulates the data and operations on a task list.
     * @param ui A duke.Ui object which deals with interactions with the user.
     * @param storage A duke.Storage object which deals with loading tasks from the file and saving tasks in the file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(newTask);
        ui.printMsg("Got it. I've added this task: ");
        ui.printMsg("  " + newTask);
        ui.printMsg("Now you have " + tasks.getNumOfTasks() + " tasks in the list.");
        storage.save(tasks);
    }

    public boolean isExit() {
        return false;
    }
}