package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UsageCommand extends Command {

    private static final Boolean toExit = false;

    @Override
    public CommandResponse getResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String message = ui.getUsage();
        return new CommandResponse(message, UsageCommand.toExit);
    }
}
