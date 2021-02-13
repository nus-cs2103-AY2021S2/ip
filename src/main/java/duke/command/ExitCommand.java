package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {

    @Override
    public CommandResult execute(Ui ui, TaskList tasks, Storage storage) {
        String exitMessage = ui.generateExitMessage();
        return new CommandResult( exitMessage, true);
    }
}
