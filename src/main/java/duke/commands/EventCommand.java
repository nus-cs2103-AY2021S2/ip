package duke.commands;

import duke.models.Event;
import duke.models.Task;

import java.time.LocalDateTime;

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
