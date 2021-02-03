package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents a bad or unrecognized command.
 *
 * @author Benedict Khoo
 */
public class InvalidCommand extends Command {
    private final String feedbackMsg;

    /**
     * Constructs an InvalidCommand with the input message to feedback to the user.
     *
     * @param feedbackMsg The feedback message for the user.
     */
    public InvalidCommand(String feedbackMsg) {
        this.feedbackMsg = feedbackMsg;
    }

    /**
     * Returns a CommandResult with a feedback message for the user.
     *
     * @param tasks   The task list used by Duke (unused).
     * @param storage The storage used by Duke (unused).
     * @return A CommandResult with a feedback message for the user.
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        return new CommandResult(feedbackMsg);
    }
}
