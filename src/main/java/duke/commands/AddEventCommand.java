package duke.commands;

import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.time.LocalDateTime;

/**
 * Handles the logic of adding an event task to the tasks list.
 */
public class AddEventCommand extends Command {
    private final String description;
    private final LocalDateTime atDateTime;

    /**
     * Initializes a command to add an event task with a description and a datetime.
     *
     * @param description Description of the event task.
     * @param atDateTime  The event's time.
     */
    public AddEventCommand(String description, LocalDateTime atDateTime) {
        this.description = description;
        this.atDateTime = atDateTime;
    }

    public boolean isExit() {
        return false;
    }

    /**
     * Creates an <code>Event</code> object and adds it to the input <code>TaskList</code>.
     * Then, prints responses to the notify the users.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     * @param ui A handler to manage the application's user-interface layer.
     */
    public void execute(TaskList tasks, Ui ui) {
        Event event = new Event(this.description, this.atDateTime);
        tasks.addTask(event);
        ui.handleAddTask(tasks, event);
    }
}
