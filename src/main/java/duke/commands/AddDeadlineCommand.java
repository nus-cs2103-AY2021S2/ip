package duke.commands;

import java.time.LocalDateTime;

import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Handles the logic of adding a <code>Deadline</code> to the to-do list.
 */
public class AddDeadlineCommand extends Command {
    private final Deadline deadline;

    /**
     * Initializes a command to add a <code>Deadline</code> with a description and a datetime.
     *
     * @param description Description of the deadline task.
     * @param byDateTime  The task's deadline.
     */
    public AddDeadlineCommand(String description, LocalDateTime byDateTime) {
        this.deadline = new Deadline(description, byDateTime);
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
     * Adds the created <code>Deadline</code> to the input <code>TaskList</code>.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     */
    public void execute(TaskList tasks) {
        tasks.addTask(this.deadline);
    }

    /**
     * Computes a response to notify the users the adding of the <code>Deadline</code>.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     * @param ui    A handler to manage the application's user-interface layer.
     * @return A <code>String</code> to respond to the adding of the <code>Deadline</code>.
     */
    public String getResponse(TaskList tasks, Ui ui) {
        return ui.handleAddTask(tasks, this.deadline);
    }
}
