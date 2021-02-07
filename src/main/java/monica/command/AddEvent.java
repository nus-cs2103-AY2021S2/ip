package monica.command;

import monica.task.Event;

/**
 * Represents a command that adds Event into the task list.
 */
public class AddEvent extends AddCommand {
    private Event task;

    /**
     * Constructor for AddEvent class.
     * @param task Event to be added.
     */
    public AddEvent(Event task) {
        super(task);
    }
}
