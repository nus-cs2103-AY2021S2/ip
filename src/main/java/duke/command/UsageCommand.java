package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UsageCommand extends Command {

    private static final Boolean toExit = false;

    /**
     * Lists all the commands currently supported
     * by the Duke chat box.
     *
     * @param tasks Core TaskList object.
     * @param ui Core Ui object.
     * @param storage Core Storage object.
     * @return Command execution response.
     */
    public CommandResponse execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String msg = ui.showUsage();
        return new CommandResponse(msg, UsageCommand.toExit);
    }
}
