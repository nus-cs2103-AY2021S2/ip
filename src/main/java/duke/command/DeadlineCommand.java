package duke.command;

import java.time.LocalDateTime;

import duke.task.DeadlineTask;

public class DeadlineCommand extends AddCommand {
    public DeadlineCommand(final String content, final LocalDateTime date) {
        super(new DeadlineTask(content, date));
    }
}
