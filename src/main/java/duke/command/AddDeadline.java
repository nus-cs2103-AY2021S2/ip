package duke.command;

import duke.task.Deadline;

public class AddDeadline extends AddCommand{
    private Deadline task;
    public AddDeadline(Deadline task) {
        super(task);
    }

}
