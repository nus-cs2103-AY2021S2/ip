package duke.command;

import duke.storage.Storage;
import duke.model.TaskList;
import duke.ui.MessageGenerator;

public class ExitCommand extends Command {

    @Override
    public CommandResult execute(MessageGenerator messageGenerator, TaskList tasks, Storage storage) {
        String exitMessage = messageGenerator.generateExitMessage();
        return new CommandResult( exitMessage, true);
    }
}
