package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {

    private static final Boolean toExit = true;

    @Override
    public CommandResponse getResponse(TaskList tasks, Ui ui, Storage storage) {
        String message = ui.getByeSuccess();
        return new CommandResponse(message, ByeCommand.toExit);
    }
}
