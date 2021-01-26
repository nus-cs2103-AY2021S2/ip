package duke.command;

import java.time.LocalDateTime;

import duke.task.EventTask;

public class EventCommand extends AddCommand {
    public EventCommand(final String content, final LocalDateTime date) {
        super(new EventTask(content, date));
    }
}
