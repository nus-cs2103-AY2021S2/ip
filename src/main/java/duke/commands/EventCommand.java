package duke.commands;

import java.time.LocalDateTime;

import duke.models.Event;
import duke.models.Task;

/**
 * Handles the Event command of the user to create new events in the list.
 * Format of command: "event [event_name] /at [datetime]".
 */
public class EventCommand extends AddCommand {
    private LocalDateTime dateTime;

    public EventCommand(String taskName, LocalDateTime dateTime) {
        super(taskName);
        this.dateTime = dateTime;
    }

    @Override
    public Task getTask() {
        return new Event(getTaskName(), dateTime);
    }
}
