package duke.commands;

import duke.models.Event;
import duke.models.Task;

import java.time.LocalDateTime;

/**
 * Handles the Event command of the user to create new events in the list.
 * Format of command: "event <event_name> /at <datetime>".
 */
public class EventCommand extends AddCommand {
    private LocalDateTime datetime;

    public EventCommand(String taskName, LocalDateTime datetime) {
        super(taskName);
        this.datetime = datetime;
    }

    @Override
    public Task getTask() {
        return new Event(getTaskName(), datetime);
    }
}
