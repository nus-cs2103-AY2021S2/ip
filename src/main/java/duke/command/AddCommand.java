package duke.command;

import duke.Ui;
import duke.Storage;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command telling duke to add a task to its task list
 */
public class AddCommand implements Command {
    Task taskToAdd;

    /**
     * Constructor
     * 
     * @param task Task to be added
     */
    public AddCommand(Task task) {
        this.taskToAdd = task;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.taskToAdd);
        String numOfTasks = tasks.size() + (tasks.size() > 1 ? " tasks" : " task");
        ui.printMessage(
                "Got it. I've added this task:\n  " + this.taskToAdd + "\nNow you have " + numOfTasks + " in the list.");
    }
}
