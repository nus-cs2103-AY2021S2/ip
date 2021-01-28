package duke.commands;

import duke.models.Event;
import duke.models.Task;

import java.time.LocalDateTime;

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
