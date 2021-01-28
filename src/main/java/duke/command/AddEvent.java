package duke.command;

import duke.task.Event;

public class AddEvent extends AddCommand {
    private Event task;
    public AddEvent(Event task) {
        super(task);
    }
}
