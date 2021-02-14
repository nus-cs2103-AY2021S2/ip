package duke.command;

import duke.Storage.Storage;
import duke.Model.TaskList;
import duke.Ui.MessageGenerator;

public class ExitCommand extends Command {

    @Override
    public CommandResult execute(MessageGenerator messageGenerator, TaskList tasks, Storage storage) {
        String exitMessage = messageGenerator.generateExitMessage();
        return new CommandResult( exitMessage, true);
    }
}
