package duke.commands;

import java.time.LocalDateTime;

import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Handles the logic of adding an <code>Event</code> to the to-do list.
 */
public class AddEventCommand extends Command {
    private final Event event;

    /**
     * Initializes a command to add an event task with a description and a datetime.
     *
     * @param description Description of the event task.
     * @param atDateTime  The event's time.
     */
    public AddEventCommand(String description, LocalDateTime atDateTime) {
        this.event = new Event(description, atDateTime);
    }

    /**
     * Returns false as adding <code>Event</code> tasks should not terminate the application.
     *
     * @return false
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Adds the created <code>Event</code> to the input <code>TaskList</code>.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     */
    public void execute(TaskList tasks) {
        tasks.addTask(this.event);
    }

    /**
     * Computes a response to notify the users the adding of the <code>Event</code>.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     * @return A <code>String</code> to respond to the adding of the <code>Event</code>.
     */
    public String getResponse(TaskList tasks) {
        return Ui.getAddTaskResponse(tasks, this.event);
    }
}
