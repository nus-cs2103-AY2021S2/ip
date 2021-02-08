package duke.commands;

import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.time.LocalDateTime;

/**
 * Handles the logic of adding a deadline task to the to-do list.
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final LocalDateTime byDateTime;

    /**
     * Initializes a command to add a deadline task with a description and a datetime.
     *
     * @param description Description of the deadline task.
     * @param byDateTime  The task's deadline.
     */
    public AddDeadlineCommand(String description, LocalDateTime byDateTime) {
        this.description = description;
        this.byDateTime = byDateTime;
    }

    public boolean isExit() {
        return false;
    }

    /**
     * Creates a <code>Deadline</code> object and adds it to the input <code>TaskList</code>.
     * Then, prints responses to the notify the users.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     * @param ui A handler to manage the application's user-interface layer.
     */
    public void execute(TaskList tasks, Ui ui) {
        Deadline deadline = new Deadline(this.description, this.byDateTime);
        tasks.addTask(deadline);
        ui.handleAddTask(tasks, deadline);
    }
}
