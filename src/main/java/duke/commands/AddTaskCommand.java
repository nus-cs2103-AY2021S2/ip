package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.TaskStringFormatter;

/**
 * Handles the logic of adding a <code>Task</code> to the to-do list.
 */
public class AddTaskCommand extends Command {
    private final Task task;

    /**
     * Initializes a command to add a <code>Task</code> to the to-do list.
     *
     * @param task The <code>Task</code> to be added to the to-do list.
     */
    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Returns false as adding <code>Deadline</code> tasks should not terminate the application.
     *
     * @return false
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Adds the created <code>Task</code> to the input <code>TaskList</code>.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     */
    public void execute(TaskList tasks) {
        tasks.addTask(this.task);
    }

    /**
     * Computes a response to notify the users the adding of the <code>Task</code>.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     * @return A <code>String</code> to respond to the adding of the <code>Deadline</code>.
     */
    public String getResponse(TaskList tasks) {
        return "Meow... I've added this task:\n"
                + "\n"
                + TaskStringFormatter.getTaskTable(this.task) + "\n"
                + "\n"
                + "Now you have " + tasks.getSize() + " task(s) in the list :)";
    }
}
