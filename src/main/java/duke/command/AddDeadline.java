package duke.command;

import duke.task.Deadline;

/**
 * Represents a command that adds Deadline into the task list.
 */
public class AddDeadline extends AddCommand{
    private Deadline task;

    /**
     * Constructor for AddDeadline class.
     * @param task Deadline to be added.
     */
    public AddDeadline(Deadline task) {
        super(task);
    }

}
