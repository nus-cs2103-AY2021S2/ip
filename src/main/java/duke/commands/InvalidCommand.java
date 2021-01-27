package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;

public class InvalidCommand extends Command {
    private final String feedbackMsg;

    public InvalidCommand(String feedbackMsg) {
        this.feedbackMsg = feedbackMsg;
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        return new CommandResult(feedbackMsg);
    }
}
