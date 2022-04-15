package duke.command;

import java.time.LocalDateTime;

import duke.task.DeadlineTask;

/**
 * Represents a command that adds a DeadlineTask to the given TaskList.
 */
public class DeadlineCommand extends AddCommand {
    /**
     * Constructs a new deadline command with the associated content and date, in a deadline task.
     *
     * @param content the content of the task.
     * @param date the deadline of the task.
     */
    public DeadlineCommand(final String content, final LocalDateTime date) {
        super(new DeadlineTask(content, date));
    }
}
