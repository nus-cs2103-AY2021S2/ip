package duke.command;

import java.time.LocalDateTime;

import duke.task.EventTask;

/**
 * Represents a command that adds a EventTask to the given TaskList.
 */
public class EventCommand extends AddCommand {
    /**
     * Constructs a new event command with the associated content and dates, in a event task.
     *
     * @param content the content of the event.
     * @param startDate the start of the event.
     * @param endDate the end of the event.
     */
    public EventCommand(final String content, final LocalDateTime startDate, final LocalDateTime endDate) {
        super(new EventTask(content, startDate, endDate));
    }
}
