package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {

    private static final Boolean toExit = true;

    @Override
    public CommandResponse getResponse(TaskList tasks, Storage storage) {
        String message = Ui.getByeSuccess();
        return new CommandResponse(message, ByeCommand.toExit);
    }
}
